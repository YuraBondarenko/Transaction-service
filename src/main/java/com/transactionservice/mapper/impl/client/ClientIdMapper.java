package com.transactionservice.mapper.impl.client;

import com.transactionservice.dto.response.client.ClientIdResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientIdMapper implements MapperToDto<ClientIdResponseDto, Client> {
    @Override
    public ClientIdResponseDto getDto(Client entity) {
        ClientIdResponseDto clientIdResponseDto = new ClientIdResponseDto();
        clientIdResponseDto.setId(entity.getId());
        return clientIdResponseDto;
    }
}
