package com.boucy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boucy.pojo.BookType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface BookTypeService extends IService<BookType> {
    String bookStoreHomePage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);
}
