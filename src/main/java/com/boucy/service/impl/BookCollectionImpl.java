package com.boucy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookCollectionMapper;
import com.boucy.pojo.BookCollection;
import com.boucy.pojo.BookShoppingCart;
import com.boucy.service.BookCollectionService;
import com.boucy.service.BookShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class BookCollectionImpl extends ServiceImpl<BookCollectionMapper, BookCollection> implements BookCollectionService {
}
