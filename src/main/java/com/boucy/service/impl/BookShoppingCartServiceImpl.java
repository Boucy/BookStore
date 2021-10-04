package com.boucy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookShoppingCartMapper;
import com.boucy.pojo.BookShoppingCart;
import com.boucy.service.BookShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class BookShoppingCartServiceImpl extends ServiceImpl<BookShoppingCartMapper, BookShoppingCart> implements BookShoppingCartService {
}
