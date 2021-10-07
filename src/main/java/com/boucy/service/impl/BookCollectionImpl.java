package com.boucy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookCollectionMapper;
import com.boucy.pojo.BookCollection;
import com.boucy.pojo.BookShoppingCart;
import com.boucy.pojo.User;
import com.boucy.service.BookCollectionService;
import com.boucy.service.BookShoppingCartService;
import com.boucy.vo.BookCollectionJoinBook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class BookCollectionImpl extends ServiceImpl<BookCollectionMapper, BookCollection> implements BookCollectionService {
    @Override
    public Page<BookCollectionJoinBook> getBookCollectionJoinBook(Page<BookCollectionJoinBook> page, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        Integer userId = user.getId();
        return page.setRecords(this.baseMapper.getBookCollectionJoinBook(page,userId.toString()));
    }
}
