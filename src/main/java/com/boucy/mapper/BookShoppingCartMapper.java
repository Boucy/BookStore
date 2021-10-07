package com.boucy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boucy.pojo.BookShoppingCart;
import com.boucy.vo.ShoppingCartJoinBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookShoppingCartMapper extends BaseMapper<BookShoppingCart> {
    @Select("SELECT * FROM book_shopping_cart a LEFT JOIN book b ON a.book_id=b.id WHERE a.user_id=#{id}")
    List<ShoppingCartJoinBook> getShoppingCartJoinBook(Page page, String id);
}
