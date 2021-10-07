package com.boucy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boucy.pojo.BookCollection;
import com.boucy.vo.BookCollectionJoinBook;
import com.boucy.vo.ShoppingCartJoinBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookCollectionMapper extends BaseMapper<BookCollection> {
    @Select("SELECT * FROM book_collection a LEFT JOIN book b ON a.book_id=b.id WHERE a.user_id=#{id}")
    List<BookCollectionJoinBook> getBookCollectionJoinBook(Page page, String id);
}
