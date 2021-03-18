package com.transactionservice.controller;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionservice.dto.request.account.AccountRequestDto;
import com.transactionservice.dto.request.client.ClientRequestDto;
import com.transactionservice.dto.response.client.ClientIdResponseDto;
import com.transactionservice.dto.response.client.ClientResponseDto;
import com.transactionservice.mapper.impl.client.ClientMapper;
import com.transactionservice.model.Client;
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
public class ClientControllerTest {
    private static final String ENDPOINT = "/clients";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void saveAndExpectStatusIsCreated_Ok() throws Exception {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("First");
        clientRequestDto.setLastName("Last");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(clientRequestDto);
        mockMvc.perform(post(ENDPOINT).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        AccountRequestDto account = new AccountRequestDto();
        account.setAccountNumber("1234");
        account.setBalance(BigDecimal.valueOf(1000));
        account.setType("SIMPLE");
        clientRequestDto.setAccounts(List.of(account));

        String contentWithAccounts = mapper.writeValueAsString(clientRequestDto);
        mockMvc.perform(post(ENDPOINT).content(contentWithAccounts)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void get_Ok() throws Exception {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("First");
        clientRequestDto.setLastName("Last");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(clientRequestDto);
        mockMvc.perform(post(ENDPOINT).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get(ENDPOINT + "/1")).andExpect(status().isOk());
    }

    @Test
    public void createResponse_Ok() throws Exception {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("First");
        clientRequestDto.setLastName("Last");

        ClientIdResponseDto clientIdResponseDto = new ClientIdResponseDto();
        clientIdResponseDto.setId(1L);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(clientRequestDto);
        String responseContent = mapper.writeValueAsString(clientIdResponseDto);

        mockMvc.perform(post(ENDPOINT).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseContent));
    }

    @Test
    public void getAllResponse_Ok() throws Exception {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("First");
        clientRequestDto.setLastName("Last");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(clientRequestDto);

        mockMvc.perform(post(ENDPOINT).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Client client = clientMapper.getEntity(clientRequestDto);
        client.setId(1L);
        List<ClientResponseDto> list = List.of(clientMapper.getDto(client));

        String response = mapper.writeValueAsString(list);

        mockMvc.perform(get(ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(response));
    }

    @Test
    public void put_Ok() throws Exception {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("First");
        clientRequestDto.setLastName("Last");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(clientRequestDto);

        mockMvc.perform(post(ENDPOINT).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        clientRequestDto.setFirstName("new First");

        content = mapper.writeValueAsString(clientRequestDto);

        ClientIdResponseDto clientIdResponseDto = new ClientIdResponseDto();
        clientIdResponseDto.setId(1L);
        String responseContent = mapper.writeValueAsString(clientIdResponseDto);

        mockMvc.perform(put(ENDPOINT + "/1").content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().string(responseContent));
    }
}
