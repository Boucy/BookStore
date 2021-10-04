package com.boucy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boucy.mapper.UserMapper;
import com.boucy.pojo.User;
import com.boucy.service.BookCommentsService;
import com.boucy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookCommentsService bookCommentsService;

    @RequestMapping("/jumpRegister")
    public String jumpRegister(){
        return "register";
    }

    @RequestMapping("/jumpLogin")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(String userID, String password, HttpServletRequest request){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userID).eq("password",password);
        User user = userService.getOne(queryWrapper);
        String view = "";
        if (null != user) {
            request.getSession().setAttribute("user", user);
            view = "redirect:../book/bookStoreHomePage";
        } else {
            view = "loginFail";
        }
        return view;
    }

    @RequestMapping("/userRegister")
    public String userRegister(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        user.setCreateDate(new Date());
//        user.setBanlance(0.0);
        return userService.userRegister(user);
    }

    @RequestMapping("/showUserInfo")
    public String showUserInfo(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return userService.showUserInfo(map,request,response);
    }

    @RequestMapping("/jumpUpdateUserInfo")
    public String jumpUpdateUserInfo(Map<String,Object> map,HttpServletRequest request){
        return userService.jumpUpdateUserInfo(map,request);
    }

    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(User user, HttpServletRequest request, HttpServletResponse response){
        return userService.updateUserInfo(user,request,response);
    }

    @RequestMapping("/showPurchaseRecord")
    public String showPurchaseRecord(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return userService.showPurchaseRecord(map,request,response);
    }

    @RequestMapping("/useridCheck")
    public void useridCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userid = request.getParameter("userid");
        String info = "";
        QueryWrapper<User> queryWarpper = new QueryWrapper<>();
        queryWarpper.eq("id", userid);
        User user = userService.getOne(queryWarpper);
        if (null == user) {
            info = "id可用";
        } else {
            info = "id已被占用";
        }
//        向浏览器响应数据
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(info);
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:../book/bookStoreHomePage";
    }

    @RequestMapping("/showPersonalBookComments")
    public String showPersonalBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return bookCommentsService.showPersonalBookComments(map,request,response);
    }
}
