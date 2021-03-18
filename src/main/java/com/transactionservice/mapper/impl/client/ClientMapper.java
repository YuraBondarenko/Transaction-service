package com.transactionservice.mapper.impl.client;

import com.transactionservice.dto.request.client.ClientRequestDto;
import com.transactionservice.dto.response.client.ClientResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.mapper.MapperToEntity;
import com.transactionservice.mapper.impl.account.AccountMapper;
import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements MapperToDto<ClientResponseDto, Client>,
        MapperToEntity<Client, ClientRequestDto> {
    private final AccountMapper accountMapper;

    public ClientMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public ClientResponseDto getDto(Client entity) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAccountNumbers(entity.getAccounts().stream()
                .map(Account::getAccountNumber)
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Client getEntity(ClientRequestDto dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        if (dto.getAccounts() != null) {
            client.setAccounts((dto.getAccounts().stream()
                    .map(accountMapper::getEntity)
                    .collect(Collectors.toList())));
        }
        return client;
    }
}
