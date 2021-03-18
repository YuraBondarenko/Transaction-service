package com.transactionservice.controller;

import com.transactionservice.dto.request.client.ClientRequestDto;
import com.transactionservice.dto.response.client.ClientIdResponseDto;
import com.transactionservice.dto.response.client.ClientResponseDto;
import com.transactionservice.mapper.impl.client.ClientIdMapper;
import com.transactionservice.mapper.impl.client.ClientMapper;
import com.transactionservice.model.Client;
import com.transactionservice.service.ClientService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    private final ClientMapper clientMapper;
    private final ClientService clientService;
    private final ClientIdMapper clientIdMapper;

    public ClientController(ClientMapper clientMapper, ClientService clientService,
                            ClientIdMapper clientIdMapper) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
        this.clientIdMapper = clientIdMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new ResponseEntity<>((clientService.getAll(page, limit, sortBy).stream()
                .map(clientMapper::getDto)
                .collect(Collectors.toList())), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientIdResponseDto> save(@RequestBody @Valid ClientRequestDto dto) {
        return new ResponseEntity<>(clientIdMapper.getDto(clientService
                .save(clientMapper.getEntity(dto))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientIdResponseDto> update(
            @PathVariable Long id, @RequestBody @Valid ClientRequestDto dto) {
        Client client = clientMapper.getEntity(dto);
        client.setId(id);
        return new ResponseEntity<>(clientIdMapper
                .getDto(clientService.save(client)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(clientMapper.getDto(clientService.getById(id)), HttpStatus.OK);
    }
}
