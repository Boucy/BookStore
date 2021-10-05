package com.boucy.controller;

import com.boucy.service.BookCommentsService;
import com.boucy.service.BookService;
import com.boucy.service.BookTypeService;
import com.boucy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagementController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookCommentsService bookCommentsService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookTypeService bookTypeService;

    @RequestMapping("/showBookManagement")
    public String showBookManagement(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return bookService.showBookManagement(map,request,response);
    }

    @RequestMapping("/showUserManagement")
    public String showUserManagement(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return userService.showUserManagement(map,request,response);
    }

    @RequestMapping("/showPurchaseManagement")
    public String showPurchaseManagement(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return userService.showPurchaseManagement(map,request,response);
    }

}
