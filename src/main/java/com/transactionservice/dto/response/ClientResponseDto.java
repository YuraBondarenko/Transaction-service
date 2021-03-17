package com.transactionservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ClientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> accountNumbers;
}
