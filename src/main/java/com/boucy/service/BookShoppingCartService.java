package com.boucy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boucy.pojo.BookShoppingCart;
import com.boucy.vo.BookJoinBookPosses;
import com.boucy.vo.ShoppingCartJoinBook;

import javax.servlet.http.HttpServletRequest;

public interface BookShoppingCartService extends IService<BookShoppingCart> {
    Page<ShoppingCartJoinBook> getShoppingCartJoinBook(Page<ShoppingCartJoinBook> page, HttpServletRequest request);
}
