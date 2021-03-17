package com.transactionservice.mapper.impl.transaction;

import com.transactionservice.dto.response.transaction.TransactionIdResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionIdMapper implements MapperToDto<TransactionIdResponseDto, Transaction> {
    @Override
    public TransactionIdResponseDto getDto(Transaction entity) {
        TransactionIdResponseDto dto = new TransactionIdResponseDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus().name().toLowerCase());
        return dto;
    }
}
