package com.boucy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boucy.mapper.UserMapper;
import com.boucy.pojo.User;
import com.boucy.service.BookCommentsService;
import com.boucy.service.MailService;
import com.boucy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private MailService mailService;

    @RequestMapping("/jumpRegister")
    public String jumpRegister() {
        return "register";
    }

    @RequestMapping("/jumpLogin")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(@RequestParam("email") String email, String password, HttpServletRequest request) {
        String view = "";
        try {
//      获取主体对象
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(email, password));
            User user = userService.findUserByEmail(email);
            request.getSession().setAttribute("user", user);
            view = "redirect:../book/bookStoreHomePage";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            view = "loginFail";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            view = "loginFail";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return view;
    }

    @RequestMapping("/userRegister")
    public String userRegister(User user,String vsCode) {
        if (!ObjectUtils.isEmpty(userService.findUserByEmail(user.getEmail()))) {
            return "registerFail";
        }
        boolean verify = mailService.verify(vsCode, user.getEmail());
        if (!verify) {
            return "registerFail";
        }

        user.setCreateDate(new Date());
//        user.setBanlance(0.0);
        return userService.userRegister(user);
    }

    @GetMapping("/sendCode")
    public Object sendCode(@RequestParam("email") String email, HttpSession session) {
        boolean b = mailService.sendMimeMail(email, session);
        return null;
    }

    @RequestMapping("/showUserInfo")
    public String showUserInfo(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return userService.showUserInfo(map, request, response);
    }

    @RequestMapping("/jumpUpdateUserInfo")
    public String jumpUpdateUserInfo(Map<String, Object> map, HttpServletRequest request) {
        return userService.jumpUpdateUserInfo(map, request);
    }

    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(User user, HttpServletRequest request, HttpServletResponse response) {
        return userService.updateUserInfo(user, request, response);
    }

    @RequestMapping("/showPurchaseRecord")
    public String showPurchaseRecord(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return userService.showPurchaseRecord(map, request, response);
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
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:../book/bookStoreHomePage";
    }

    @RequestMapping("/showPersonalBookComments")
    public String showPersonalBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookCommentsService.showPersonalBookComments(map, request, response);
    }

    @RequestMapping("/jumpAdmin")
    public String jumpAdmin(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return "administrator";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        return userService.deleteUser(request, response);
    }

    @RequestMapping("/showRefuseAdmin")
    public String showRefuseAdmin() {
        return "refuseAdmin";
    }
}
