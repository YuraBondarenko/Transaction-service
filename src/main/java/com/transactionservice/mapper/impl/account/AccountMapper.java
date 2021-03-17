package com.transactionservice.mapper.impl.account;

import com.transactionservice.dto.request.account.AccountRequestDto;
import com.transactionservice.dto.response.account.AccountResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.mapper.MapperToEntity;
import com.transactionservice.model.Account;
import org.springframework.stereotype.Component;

@Component
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
