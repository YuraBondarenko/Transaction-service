package com.transactionservice.controller;

import com.transactionservice.dto.request.transaction.TransactionInformationRequestDto;
import com.transactionservice.dto.request.transaction.TransactionRequestDto;
import com.transactionservice.dto.response.transaction.TransactionIdResponseDto;
import com.transactionservice.dto.response.transaction.TransactionResponseDto;
import com.transactionservice.mapper.impl.transaction.TransactionIdMapper;
import com.transactionservice.mapper.impl.transaction.TransactionInformationMapper;
import com.transactionservice.mapper.impl.transaction.TransactionMapper;
import com.transactionservice.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    private final TransactionIdMapper transactionIdMapper;
    private final TransactionInformationMapper transactionInformationMapper;

    public TransactionController(
            TransactionService transactionService, TransactionMapper transactionMapper,
            TransactionIdMapper transactionIdMapper,
            TransactionInformationMapper transactionInformationMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
        this.transactionIdMapper = transactionIdMapper;
        this.transactionInformationMapper = transactionInformationMapper;
    }

    @PostMapping
    public ResponseEntity<List<TransactionIdResponseDto>> saveAll(
            @RequestBody List<TransactionRequestDto> dtos) {
        List<TransactionIdResponseDto> responseDtos = new ArrayList<>();
        for (TransactionRequestDto dto : dtos) {
            responseDtos.add(transactionIdMapper.getDto(transactionService
                    .save(transactionMapper.getEntity(dto))));
        }
        return new ResponseEntity<>(responseDtos, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> get(
            @RequestBody TransactionInformationRequestDto dto) {
        return new ResponseEntity<>(transactionInformationMapper.getEntity(dto), HttpStatus.OK);
    }
}
