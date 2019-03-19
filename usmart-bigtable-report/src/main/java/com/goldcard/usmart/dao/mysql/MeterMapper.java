package com.goldcard.usmart.dao.mysql;

import com.goldcard.usmart.domain.Meter;
import com.goldcard.usmart.domain.report.query.CollectionRecordQueryDTO;

public interface MeterMapper {
    int deleteByPrimaryKey(String meterId);

    int insert(Meter record);

    int insertSelective(Meter record);

    Meter selectByPrimaryKey(String meterId);

    int updateByPrimaryKeySelective(Meter record);

    int updateByPrimaryKey(Meter record);
    
    long countWithResidentMeter(CollectionRecordQueryDTO queryDTO);
    long countWithBusinessMeter(CollectionRecordQueryDTO queryDTO);
    long countWithDTU(CollectionRecordQueryDTO queryDTO);
    long countWithOther(CollectionRecordQueryDTO queryDTO);
}