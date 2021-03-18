package com.transactionservice.controller;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionservice.dto.request.account.AccountRequestDto;
import com.transactionservice.dto.response.account.AccountResponseDto;
import com.transactionservice.model.Client;
import com.transactionservice.service.ClientService;
import java.math.BigDecimal;
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
public class AccountControllerTest {
    private static final String ENDPOINT = "/accounts";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientService clientService;

    @Test
    public void createAndGet_Ok() throws Exception {
        Client client = new Client();
        client.setFirstName("First");
        client.setLastName("Last");
        clientService.save(client);

        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setAccountNumber("123456789");
        accountRequestDto.setType("SIMPLE");
        accountRequestDto.setBalance(BigDecimal.valueOf(5000));

        ObjectMapper mapper = new ObjectMapper();

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setCardType(accountRequestDto.getType());
        accountResponseDto.setId(1L);
        accountResponseDto.setAccountNumber(accountRequestDto.getAccountNumber());
        accountResponseDto.setBalance(accountRequestDto.getBalance());

        String content = mapper.writeValueAsString(accountRequestDto);
        String response = mapper.writeValueAsString(accountResponseDto);
        mockMvc.perform(post(ENDPOINT + "/client-id/1").content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(response));
    }
}
