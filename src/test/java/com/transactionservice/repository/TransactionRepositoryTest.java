package com.transactionservice.repository;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import com.transactionservice.model.Transaction;
import com.transactionservice.service.ClientService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@Transactional
public class TransactionRepositoryTest {
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void getByAccounts_Ok() {
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

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1000));
        transaction.setAccountTo(account);
        transaction.setAccountFrom(secondAccount);
        transaction.setReason("reason");

        transactionRepository.save(transaction);

        List<Transaction> actual = transactionRepository
                .getAllByAccountFromAndAccountFrom(secondAccount, account);
        Assertions.assertEquals(1, actual.size());
    }
}
