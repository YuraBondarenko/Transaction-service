package com.transactionservice.mapper.impl;

import com.transactionservice.dto.request.TransactionRequestDto;
import com.transactionservice.dto.response.TransactionResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.mapper.MapperToEntity;
import com.transactionservice.model.Transaction;
import com.transactionservice.service.AccountService;

public class TransactionMapper implements MapperToDto<TransactionResponseDto, Transaction>,
        MapperToEntity<Transaction, TransactionRequestDto> {
    private final AccountService accountService;

    public TransactionMapper(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public TransactionResponseDto getDto(Transaction entity) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(entity.getId());
        dto.setTimestamp(entity.getTimestamp());
        dto.setAccountNumberFrom(entity.getAccountFrom().getAccountNumber());
        dto.setAccountNumberTo(entity.getAccountTo().getAccountNumber());
        dto.setReason(entity.getReason());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    @Override
    public Transaction getEntity(TransactionRequestDto dto) {
        Transaction transaction = new Transaction();
        transaction.setReason(dto.getReason());
        transaction.setAccountFrom(accountService.getByNumber(dto.getAccountFrom()));
        transaction.setAccountTo(accountService.getByNumber(dto.getAccountTo()));
        transaction.setAmount(dto.getAmount());
        return transaction;
    }
}
