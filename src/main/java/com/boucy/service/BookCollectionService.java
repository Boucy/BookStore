package com.boucy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boucy.pojo.BookCollection;
import com.boucy.pojo.BookShoppingCart;
import com.boucy.vo.BookCollectionJoinBook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BookCollectionService extends IService<BookCollection> {
    Page<BookCollectionJoinBook> getBookCollectionJoinBook(Page<BookCollectionJoinBook> bookCollectionJoinBookPage, HttpServletRequest request);
}
