package com.boucy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookMapper;
import com.boucy.mapper.PurchaseRecordMapper;
import com.boucy.mapper.UserMapper;
import com.boucy.pojo.Book;
import com.boucy.pojo.PurchaseRecord;
import com.boucy.pojo.User;
import com.boucy.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
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
        queryWrapper.eq("id", user.getEmail());
        User one = userMapper.selectOne(queryWrapper);
        if (one != null) {
            return "registerFail";
        }
        Md5Hash md5Hash = new Md5Hash(user.getPassword());
        user.setPassword(md5Hash.toHex());
        userMapper.insert(user);
        return "registerSuccess";
    }

    @Override
    public String showUserInfo(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user;
        String userID = request.getParameter("userID");
        if (userID == null || userID == "") {
            user = (User) request.getSession().getAttribute("user");
        } else {
            user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userID));
        }
        map.put("user", user);
        return "userInfo";
    }

    @Override
    public String updateUserInfo(User user, HttpServletRequest request, HttpServletResponse response) {
        String password = user.getPassword();
        Md5Hash md5Hash = new Md5Hash(password);
        password=md5Hash.toHex();
        User originalUser = (User)request.getSession().getAttribute("user");
        if(!password.equals(originalUser.getPassword())){
            user.setPassword(password);
        }
        request.getSession().setAttribute("user", user);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", user.getId());
        userMapper.update(user, userQueryWrapper);
        return "redirect:/user/showUserInfo";
    }

    @Override
    public String jumpUpdateUserInfo(Map<String, Object> map, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userID = request.getParameter("userID");
        if (userID == null || userID == "") {
            User userSearch = (User) request.getSession().getAttribute("user");
        } else {
            if ((!user.getUserType().equals("1")) && (!(user.getId().toString()).equals(userID))) {
                return "refuseAdmin";
            }
            user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userID));
        }
        map.put("user", user);
        return "updateUserInfoPage";
    }

    @Override
    public String showPurchaseRecord(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        List<PurchaseRecord> purchaseRecordList = purchaseRecordMapper.findPurchaseJoinBookByUserID(userid);
        map.put("purchaseRecordList", purchaseRecordList);
        return "purchaseRecord";
    }

    @Override
    public User findUserByEmail(String principal) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("email", principal));
    }

    @Override
    public String showUserManagement(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String index = request.getParameter("index");
        Page<User> page = userMapper.selectPage(new Page<>(Integer.parseInt(index), 10), null);
        List<User> userList = page.getRecords();
        map.put("userList", userList);
//        总记录数
        map.put("total", page.getTotal());
//        总页数
        map.put("pageCount", page.getPages());
//        当前页
        map.put("pageIndex", page.getCurrent());
//        页大小
        map.put("pageSize", page.getSize());
        return "userManagement";
    }

    @Override
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String userID = request.getParameter("userID");
        userMapper.delete(new QueryWrapper<User>().eq("id", userID));
        return "redirect:../manager/showUserManagement";
    }

    @Override
    public Page<PurchaseRecord> showPurchaseManagement(Page<PurchaseRecord> page, HttpServletRequest request, HttpServletResponse response) {
        return page.setRecords(this.purchaseRecordMapper.findAllPurchaseJoinBook(page));
    }
}
