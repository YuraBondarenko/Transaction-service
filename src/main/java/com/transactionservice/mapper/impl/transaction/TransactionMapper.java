package com.transactionservice.mapper.impl.transaction;

import com.transactionservice.dto.request.transaction.TransactionRequestDto;
import com.transactionservice.dto.response.transaction.TransactionResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.mapper.MapperToEntity;
import com.transactionservice.mapper.impl.client.ClientNameMapper;
import com.transactionservice.model.Transaction;
import com.transactionservice.service.AccountService;
import com.transactionservice.service.ClientService;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements MapperToDto<TransactionResponseDto, Transaction>,
        MapperToEntity<Transaction, TransactionRequestDto> {
    private final AccountService accountService;
    private final ClientService clientService;
    private final ClientNameMapper clientNameMapper;

    public TransactionMapper(
            AccountService accountService, ClientService clientService,
            ClientNameMapper clientNameMapper) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.clientNameMapper = clientNameMapper;
    }

    @Override
    public TransactionResponseDto getDto(Transaction entity) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(entity.getId());
        dto.setTimestamp(entity.getTimestamp());
        dto.setAccountNumberFrom(entity.getAccountFrom().getAccountNumber());
        dto.setAccountNumberTo(entity.getAccountTo().getAccountNumber());
        dto.setAmount(entity.getAmount());
        dto.setPayer(clientNameMapper.getDto(clientService.getByAccount(entity.getAccountFrom())));
        dto.setRecipient(clientNameMapper.getDto(clientService
                .getByAccount(entity.getAccountTo())));
        return dto;
    }

    @Override
    public Transaction getEntity(TransactionRequestDto dto) {
        Transaction transaction = new Transaction();
        transaction.setReason(dto.getReason());
        transaction.setAccountFrom(accountService.getByNumber(dto.getAccountNumberFrom()));
        transaction.setAccountTo(accountService.getByNumber(dto.getAccountNumberTo()));
        transaction.setAmount(dto.getAmount());
        return transaction;
    }
}
