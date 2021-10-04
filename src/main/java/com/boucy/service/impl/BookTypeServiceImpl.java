package com.boucy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookMapper;
import com.boucy.mapper.BookTypeMapper;
import com.boucy.pojo.BookType;
import com.boucy.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {
    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Override
    public String bookStoreHomePage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        List<BookType> bookTypeList = bookTypeMapper.selectList(null);
        map.put("bookTypeList",bookTypeList);
        return "index";
    }
}
