package com.transactionservice.mapper.impl;

import com.transactionservice.dto.request.AccountRequestDto;
import com.transactionservice.dto.response.AccountResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.mapper.MapperToEntity;
import com.transactionservice.model.Account;

public class AccountMapper implements MapperToDto<AccountResponseDto, Account>,
        MapperToEntity<Account, AccountRequestDto> {
    @Override
    public AccountResponseDto getDto(Account entity) {
        AccountResponseDto dto = new AccountResponseDto();
        dto.setId(entity.getId());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setBalance(entity.getBalance());
        dto.setCardType(entity.getCardType().name());
        return dto;
    }

    @Override
    public Account getEntity(AccountRequestDto dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setCardType(Account.Type.valueOf(dto.getType()));
        account.setBalance(dto.getBalance());
        return account;
    }
}
