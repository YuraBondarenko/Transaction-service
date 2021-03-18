package com.transactionservice.mapper.impl.transaction;

import com.transactionservice.dto.request.transaction.TransactionInformationRequestDto;
import com.transactionservice.dto.response.transaction.TransactionResponseDto;
import com.transactionservice.mapper.MapperToEntity;
import com.transactionservice.mapper.impl.client.ClientNameMapper;
import com.transactionservice.model.Account;
import com.transactionservice.model.Transaction;
import com.transactionservice.service.AccountService;
import com.transactionservice.service.ClientService;
import com.transactionservice.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TransactionInformationMapper implements MapperToEntity<List<TransactionResponseDto>,
        TransactionInformationRequestDto> {
    private final ClientService clientService;
    private final AccountService accountService;
    private final ClientNameMapper clientNameMapper;
    private final TransactionService transactionService;

    public TransactionInformationMapper(
            ClientService clientService, AccountService accountService,
            ClientNameMapper clientNameMapper, TransactionService transactionService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.clientNameMapper = clientNameMapper;
        this.transactionService = transactionService;
    }

    @Override
    public List<TransactionResponseDto> getEntity(TransactionInformationRequestDto dto) {
        Account accountFrom = accountService.getById(dto.getAccountFromId());
        Account accountTo = accountService.getById(dto.getAccountToId());
        List<Transaction> transactions = transactionService.getByAccounts(accountFrom, accountTo);
        List<TransactionResponseDto> dtos = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            TransactionResponseDto responseDto = new TransactionResponseDto();
            responseDto.setAccountNumberFrom(accountFrom.getAccountNumber());
            responseDto.setAccountNumberTo(accountTo.getAccountNumber());
            responseDto.setPayer(clientNameMapper.getDto(clientService.getById(dto.getPayerId())));
            responseDto.setRecipient(clientNameMapper.getDto(clientService
                    .getById(dto.getRecipientId())));
            responseDto.setAmount(transactions.get(i).getAmount());
            responseDto.setTimestamp(transactions.get(i).getTimestamp());
            responseDto.setId(transactions.get(i).getId());
            responseDto.setStatus(transactions.get(i).getStatus().name().toLowerCase());
            dtos.add(responseDto);
        }
        return dtos;
    }
}
