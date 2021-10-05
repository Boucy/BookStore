package com.boucy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boucy.pojo.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface BookService extends IService<Book> {
    String bookSearchByTypeID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);
    String bookSearchByID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String cancelShoppingCart(HttpServletRequest request, HttpServletResponse response);

    String showShoppingCart(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String addShoppingCart(HttpServletRequest request, HttpServletResponse response);

    String collect(HttpServletRequest request, HttpServletResponse response);

    String cancelCollect(HttpServletRequest request, HttpServletResponse response);

    String showCollect(Map<String,Object> map,HttpServletRequest request, HttpServletResponse response);

    String rankingList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String bookSearchByKey(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String purchaseBook(HttpServletRequest request, HttpServletResponse response);

    String personalBook(Map<String, Object> map,HttpServletRequest request, HttpServletResponse response);

    String showAddComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String showBookManagement(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String showUpdateBookPage(Map<String,Object> map,HttpServletRequest request, HttpServletResponse response);

    String updateBook(String originBookID,Book book, HttpServletRequest request, HttpServletResponse response);

    String deleteBook(HttpServletRequest request, HttpServletResponse response);
}
