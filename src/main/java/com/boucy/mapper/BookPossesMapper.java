package com.boucy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boucy.pojo.BookPosses;
import com.boucy.vo.BookJoinBookPosses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookPossesMapper extends BaseMapper<BookPosses> {
    @Select("SELECT * FROM book_posses a LEFT JOIN book b ON a.book_id=b.id WHERE a.user_id=#{id}")
    List<BookJoinBookPosses> getBookJoinBookPosses(Page page,String id);
}
