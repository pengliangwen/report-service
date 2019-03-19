package com.goldcard.usmart.dao.ch;

import java.util.List;

import com.goldcard.usmart.domain.CollectionRecord;
import com.goldcard.usmart.domain.report.query.CollectionRecordQueryDTO;
import com.goldcard.usmart.domain.report.result.CollectionRecordResultDTO;

public interface CollectionRecordMapper {
    

    List<CollectionRecordResultDTO> findByQueryDTO(CollectionRecordQueryDTO dto);
    
    void insertCollectionRecord(CollectionRecord record);
    
    void insert(CollectionRecord record);
}
