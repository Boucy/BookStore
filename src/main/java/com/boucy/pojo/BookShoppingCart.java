package com.boucy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("book_shopping_cart")
public class BookShoppingCart {
    @TableField("user_id")
    private Integer userid;
    @TableField("book_id")
    private Integer bookid;
}
