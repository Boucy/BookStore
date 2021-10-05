package com.boucy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("purchase_record")
public class PurchaseRecord {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("user_id")
    private Integer userid;
    @TableField("book_id")
    private Integer bookid;
    @TableField("purchase_date")
    private Date purchaseDate;
    @TableField("price")
    private Double price;

    @TableField(exist = false)
    private Book book;
}
