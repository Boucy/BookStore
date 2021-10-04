package com.boucy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boucy.mapper.UserMapper;
import com.boucy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService extends IService<User> {
    String userRegister(User user);

    String showUserInfo(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String updateUserInfo(User user, HttpServletRequest request, HttpServletResponse response);

    String jumpUpdateUserInfo(Map<String,Object> map,HttpServletRequest request);

    String showPurchaseRecord(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

}
