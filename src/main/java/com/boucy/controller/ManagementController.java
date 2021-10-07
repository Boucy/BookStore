package com.boucy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boucy.pojo.PurchaseRecord;
import com.boucy.service.BookCommentsService;
import com.boucy.service.BookService;
import com.boucy.service.BookTypeService;
import com.boucy.service.UserService;
import com.boucy.vo.ShoppingCartJoinBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
        String index = request.getParameter("index");
        Page<PurchaseRecord> page = userService.showPurchaseManagement(new Page<PurchaseRecord>(Integer.parseInt(index), 5),request,response);
        List<PurchaseRecord> purchaseRecordList = page.getRecords();
//        页数据
        map.put("purchaseRecordList", purchaseRecordList);
//        总记录数
        map.put("total", page.getTotal());
//        总页数
        map.put("pageCount", page.getPages());
//        当前页
        map.put("pageIndex", page.getCurrent());
//        页大小
        map.put("pageSize", page.getSize());
        return "purchaseRecordManagement";
    }

    @RequestMapping("/showAddBookPage")
    public String showAddBookPage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return "addBookPage";
    }
}
