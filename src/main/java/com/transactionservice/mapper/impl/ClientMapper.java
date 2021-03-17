package com.transactionservice.mapper.impl;

import com.transactionservice.dto.request.ClientRequestDto;
import com.transactionservice.dto.response.ClientResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.mapper.MapperToEntity;
import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import java.util.stream.Collectors;

public class ClientMapper implements MapperToDto<ClientResponseDto, Client>,
        MapperToEntity<Client, ClientRequestDto> {
    @Override
    public ClientResponseDto getDto(Client entity) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
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
        client.setAccounts(dto.getAccounts());
        return client;
    }
}
