package com.boucy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boucy.pojo.Book;
import com.boucy.pojo.PurchaseRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseRecordMapper extends BaseMapper<PurchaseRecord> {
}
