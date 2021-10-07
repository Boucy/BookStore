package com.boucy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookShoppingCartMapper;
import com.boucy.pojo.BookShoppingCart;
import com.boucy.pojo.User;
import com.boucy.service.BookShoppingCartService;
import com.boucy.vo.BookJoinBookPosses;
import com.boucy.vo.ShoppingCartJoinBook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BookShoppingCartServiceImpl extends ServiceImpl<BookShoppingCartMapper, BookShoppingCart> implements BookShoppingCartService {
    @Override
    public Page<ShoppingCartJoinBook> getShoppingCartJoinBook(Page<ShoppingCartJoinBook> page, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        Integer userId = user.getId();
        return page.setRecords(this.baseMapper.getShoppingCartJoinBook(page,userId.toString()));
    }
}
