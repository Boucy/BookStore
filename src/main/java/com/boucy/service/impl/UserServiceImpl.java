package com.boucy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookMapper;
import com.boucy.mapper.PurchaseRecordMapper;
import com.boucy.mapper.UserMapper;
import com.boucy.pojo.PurchaseRecord;
import com.boucy.pojo.User;
import com.boucy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public String userRegister(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",user.getId());
        User one = userMapper.selectOne(queryWrapper);
        if(one!=null){
            return "registerFail";
        }
        userMapper.insert(user);
        return "registerSuccess";
    }

    @Override
    public String showUserInfo(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        map.put("user",user);
        return "userInfo";
    }

    @Override
    public String updateUserInfo(User user, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user",user);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",user.getId());
        userMapper.update(user,userQueryWrapper);
        return "redirect:/user/showUserInfo";
    }

    @Override
    public String jumpUpdateUserInfo(Map<String,Object> map,HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        map.put("user",user);
        return "updateUserInfoPage";
    }

    @Override
    public String showPurchaseRecord(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        QueryWrapper<PurchaseRecord> purchaseRecordQueryWrapper = new QueryWrapper<>();
        purchaseRecordQueryWrapper.eq("user_id",userid);
        List<PurchaseRecord> purchaseRecordList = purchaseRecordMapper.selectList(purchaseRecordQueryWrapper);
        map.put("purchaseRecordList",purchaseRecordList);
        return "purchaseRecord";
    }
}
