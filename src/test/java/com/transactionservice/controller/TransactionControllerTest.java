package com.transactionservice.controller;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionservice.dto.request.transaction.TransactionRequestDto;
import com.transactionservice.dto.response.transaction.TransactionIdResponseDto;
import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import com.transactionservice.service.ClientService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class TransactionControllerTest {
    private static final String ENDPOINT = "/transactions";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientService clientService;

    @Test
    public void saveAndGet_Ok() throws Exception {
        Client client = new Client();
        client.setLastName("Last");
        client.setFirstName("First");
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(5000));
        account.setCardType(Account.Type.SIMPLE);
        account.setAccountNumber("1");

        Account secondAccount = new Account();
        secondAccount.setBalance(BigDecimal.valueOf(5000));
        secondAccount.setCardType(Account.Type.SIMPLE);
        secondAccount.setAccountNumber("2");
        client.setAccounts(List.of(account, secondAccount));
        clientService.save(client);

        TransactionRequestDto requestDto = new TransactionRequestDto();
        requestDto.setAmount(BigDecimal.valueOf(1000));
        requestDto.setAccountNumberTo("1");
        requestDto.setAccountNumberFrom("2");
        requestDto.setReason("reason");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(List.of(requestDto));

        TransactionIdResponseDto transactionIdResponseDto = new TransactionIdResponseDto();
        transactionIdResponseDto.setStatus("ok");
        transactionIdResponseDto.setId(1L);

        String response = mapper.writeValueAsString(List.of(transactionIdResponseDto));
        mockMvc.perform(post(ENDPOINT).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(response));
    }
}
