package com.transactionservice.mapper.impl.client;

import com.transactionservice.dto.response.client.ClientNameResponseDto;
import com.transactionservice.mapper.MapperToDto;
import com.transactionservice.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientNameMapper implements MapperToDto<ClientNameResponseDto, Client> {
    @Override
    public ClientNameResponseDto getDto(Client entity) {
        ClientNameResponseDto dto = new ClientNameResponseDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}
