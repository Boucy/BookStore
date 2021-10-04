package com.boucy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boucy.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.MappedJdbcTypes;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
