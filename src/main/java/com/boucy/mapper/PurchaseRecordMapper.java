package com.boucy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boucy.pojo.Book;
import com.boucy.pojo.BookComments;
import com.boucy.pojo.PurchaseRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseRecordMapper extends BaseMapper<PurchaseRecord> {
    public List<PurchaseRecord> findPurchaseJoinBookByUserID(int userid);
    public List<PurchaseRecord> findAllPurchaseJoinBook();
}
