package com.boucy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boucy.pojo.Book;
import com.boucy.pojo.BookType;
import com.boucy.pojo.User;
import com.boucy.service.BookCommentsService;
import com.boucy.service.BookService;
import com.boucy.service.BookTypeService;
import com.boucy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookTypeService bookTypeService;
    @Autowired
    private BookCommentsService bookCommentsService;

    @RequestMapping("/bookSearchByKey")
    public String bookSearchByKey(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return bookService.bookSearchByKey(map,request,response);
    }

    @RequestMapping("/bookStoreHomePage")
    public String bookStoreHomePage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            map.put("indexMessage","你好!游客，请登录");
        }else{
            map.put("indexMessage","你好!"+user.getNickname());
        }
        return bookTypeService.bookStoreHomePage(map, request, response);
    }

    @RequestMapping("/bookSearchByTypeID")
    public String bookSearchByTypeID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookService.bookSearchByTypeID(map, request, response);
    }

    @RequestMapping("/rankingList")
    public String rankingList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return bookService.rankingList(map,request,response);
    }

    @RequestMapping("/bookSearchByID")
    public String bookSearchByID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return bookService.bookSearchByID(map,request,response);
    }

    @RequestMapping("/purchaseBook")
    public String purchaseBook(HttpServletRequest request, HttpServletResponse response){
        return bookService.purchaseBook(request,response);
    }

    @RequestMapping("/personalBook")
    public String personalBook(Map<String, Object> map,HttpServletRequest request, HttpServletResponse response){
        return bookService.personalBook(map,request,response);
    }

    @RequestMapping("/showShoppingCart")
    public String showShoppingCart(Map<String,Object> map,HttpServletRequest request, HttpServletResponse response){
        return bookService.showShoppingCart(map,request,response);
    }

    @RequestMapping("/addShoppingCart")
    public String addShoppingCart(HttpServletRequest request, HttpServletResponse response){
        return bookService.addShoppingCart(request,response);
    }

    @RequestMapping("/cancelShoppingCart")
    public String cancelShoppingCart(HttpServletRequest request, HttpServletResponse response){
        return bookService.cancelShoppingCart(request,response);
    }

    @RequestMapping("/showCollect")
    public String showCollect(Map<String,Object> map,HttpServletRequest request, HttpServletResponse response){
        return bookService.showCollect(map,request,response);
    }

    @RequestMapping("/collect")
    public String collect(HttpServletRequest request, HttpServletResponse response){
        return bookService.collect(request,response);
    }

    @RequestMapping("/cancelCollect")
    public String cancelCollect(HttpServletRequest request, HttpServletResponse response){
        return bookService.cancelCollect(request,response);
    }
    @RequestMapping("/showBookComments")
    public String showPersonalBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        return bookCommentsService.showBookComments(map,request,response);
    }
    @RequestMapping("/showAddComments")
    public String showAddComments(Map<String,Object> map,HttpServletRequest request, HttpServletResponse response){
        return bookService.showAddComments(map,request,response);
    }
    @RequestMapping("/addComments")
    public String addComments(@RequestParam("bookid") String bookid,@RequestParam("good") String good,@RequestParam("bad") String bad,@RequestParam("comments") String comments, HttpServletRequest request, HttpServletResponse response){
        return bookCommentsService.addComments(bookid,good,bad,comments,request,response);
    }

    @RequestMapping("/showUpdateBookPage")
    public String showUpdateBookPage(Map<String,Object> map,HttpServletRequest request, HttpServletResponse response){
        return bookService.showUpdateBookPage(map,request,response);
    }

    @RequestMapping("/updateBook")
    public String updateBook(String originBookID,Book book, HttpServletRequest request, HttpServletResponse response){
        return bookService.updateBook(originBookID,book,request,response);
    }
}
