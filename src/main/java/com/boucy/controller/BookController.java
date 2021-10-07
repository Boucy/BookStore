package com.boucy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boucy.pojo.Book;
import com.boucy.pojo.User;
import com.boucy.service.*;
import com.boucy.vo.BookCollectionJoinBook;
import com.boucy.vo.BookJoinBookPosses;
import com.boucy.vo.ShoppingCartJoinBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private BookPossesService bookPossesService;
    @Autowired
    private BookShoppingCartService bookShoppingCartService;
    @Autowired
    private BookCollectionService bookCollectionService;

    @RequestMapping("/bookSearchByKey")
    public String bookSearchByKey(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookService.bookSearchByKey(map, request, response);
    }

    @RequestMapping("/bookStoreHomePage")
    public String bookStoreHomePage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            map.put("indexMessage", "你好!游客，请登录");
        } else {
            map.put("indexMessage", "你好!" + user.getNickname());
        }
        return bookTypeService.bookStoreHomePage(map, request, response);
    }

    @RequestMapping("/bookSearchByTypeID")
    public String bookSearchByTypeID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookService.bookSearchByTypeID(map, request, response);
    }

    @RequestMapping("/rankingList")
    public String rankingList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookService.rankingList(map, request, response);
    }

    @RequestMapping("/bookSearchByID")
    public String bookSearchByID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookService.bookSearchByID(map, request, response);
    }

    @RequestMapping("/purchaseBook")
    public String purchaseBook(HttpServletRequest request, HttpServletResponse response) {
        return bookService.purchaseBook(request, response);
    }

    @RequestMapping("/personalBook")
    public String getPersonalBookByPage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String index = request.getParameter("index");
        Page<BookJoinBookPosses> page = bookPossesService.getBookJoinBookPosses(new Page<BookJoinBookPosses>(Integer.parseInt(index), 5), request);
        List<BookJoinBookPosses> bookList = page.getRecords();
//        页数据
        map.put("bookList", bookList);
//        总记录数
        map.put("total", page.getTotal());
//        总页数
        map.put("pageCount", page.getPages());
//        当前页
        map.put("pageIndex", page.getCurrent());
//        页大小
        map.put("pageSize", page.getSize());
        return "showPersonalBooks";
    }


    @RequestMapping("/showShoppingCart")
    public String showShoppingCart(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String index = request.getParameter("index");
        Page<ShoppingCartJoinBook> page = bookShoppingCartService.getShoppingCartJoinBook(new Page<ShoppingCartJoinBook>(Integer.parseInt(index), 5), request);
        List<ShoppingCartJoinBook> bookList = page.getRecords();
//        页数据
        map.put("bookList", bookList);
//        总记录数
        map.put("total", page.getTotal());
//        总页数
        map.put("pageCount", page.getPages());
//        当前页
        map.put("pageIndex", page.getCurrent());
//        页大小
        map.put("pageSize", page.getSize());
        return "shoppingCart";
    }

    @RequestMapping("/addShoppingCart")
    public String addShoppingCart(HttpServletRequest request, HttpServletResponse response) {
        return bookService.addShoppingCart(request, response);
    }

    @RequestMapping("/cancelShoppingCart")
    public String cancelShoppingCart(HttpServletRequest request, HttpServletResponse response) {
        return bookService.cancelShoppingCart(request, response);
    }

    @RequestMapping("/showCollect")
    public String showCollect(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String index = request.getParameter("index");
        Page<BookCollectionJoinBook> page = bookCollectionService.getBookCollectionJoinBook(new Page<BookCollectionJoinBook>(Integer.parseInt(index),5),request);
        List<BookCollectionJoinBook> bookList = page.getRecords();
//        页数据
        map.put("bookList", bookList);
//        总记录数
        map.put("total", page.getTotal());
//        总页数
        map.put("pageCount", page.getPages());
//        当前页
        map.put("pageIndex", page.getCurrent());
//        页大小
        map.put("pageSize", page.getSize());
        return "bookCollect";
    }

    @RequestMapping("/collect")
    public String collect(HttpServletRequest request, HttpServletResponse response) {
        return bookService.collect(request, response);
    }

    @RequestMapping("/cancelCollect")
    public String cancelCollect(HttpServletRequest request, HttpServletResponse response) {
        return bookService.cancelCollect(request, response);
    }

    @RequestMapping("/showBookComments")
    public String showPersonalBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookCommentsService.showBookComments(map, request, response);
    }

    @RequestMapping("/showAddComments")
    public String showAddComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookService.showAddComments(map, request, response);
    }

    @RequestMapping("/addComments")
    public String addComments(@RequestParam("bookid") String bookid, @RequestParam("good") String good, @RequestParam("bad") String bad, @RequestParam("comments") String comments, HttpServletRequest request, HttpServletResponse response) {
        return bookCommentsService.addComments(bookid, good, bad, comments, request, response);
    }

    @RequestMapping("/addBook")
    public String addBook(Book book, HttpServletRequest request, HttpServletResponse response) {
        return bookService.addBook(book, request, response);
    }

    @RequestMapping("/showUpdateBookPage")
    public String showUpdateBookPage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        return bookService.showUpdateBookPage(map, request, response);
    }

    @RequestMapping("/updateBook")
    public String updateBook(String originBookID, Book book, HttpServletRequest request, HttpServletResponse response) {
        return bookService.updateBook(originBookID, book, request, response);
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(HttpServletRequest request, HttpServletResponse response) {
        return bookService.deleteBook(request, response);
    }

    @RequestMapping("/purchaseMultpleBook")
    public String purchaseMultpleBook(@RequestParam("booksID") String[] booksID, HttpServletRequest request, HttpServletResponse response) {
        return bookService.purchaseMultpleBook(booksID, request, response);
    }
}
