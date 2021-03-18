package com.transactionservice.controller;

import com.transactionservice.dto.request.account.AccountRequestDto;
import com.transactionservice.dto.response.account.AccountResponseDto;
import com.transactionservice.mapper.impl.account.AccountMapper;
import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import com.transactionservice.service.AccountService;
import com.transactionservice.service.ClientService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
    private final AccountService accountService;
    private final ClientService clientService;
    private final AccountMapper accountMapper;

    public AccountController(
            AccountService accountService,
            ClientService clientService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/client-id/{id}")
    public ResponseEntity<List<AccountResponseDto>> getByClientId(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.getById(id).getAccounts().stream()
                .map(accountMapper::getDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/client-id/{id}")
    @Transactional
    public ResponseEntity<AccountResponseDto> save(
            @PathVariable Long id, @RequestBody @Valid AccountRequestDto dto) {
        Client client = clientService.getById(id);
        Account account = accountService.save(accountMapper.getEntity(dto));
        client.getAccounts().add(account);
        clientService.save(client);
        return new ResponseEntity<>(accountMapper.getDto(account), HttpStatus.CREATED);
    }
}
