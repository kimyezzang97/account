package org.example.account.service;

import org.example.account.domain.Account;
import org.example.account.domain.AccountUser;
import org.example.account.dto.AccountDto;
import org.example.account.repository.AccountRepository;
import org.example.account.repository.AccountUSerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountUSerRepository accountUserRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccountSuccess(){
        //given
        AccountUser user = AccountUser.builder()
                .Id(12L)
                .name("Pobi")
                .build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));
        given(accountRepository.findFirstByOrderByIdDesc())
                .willReturn(Optional.of(Account.builder()
                        .accountUser(user)
                        .accountNumber("1000000012").build()));

        given(accountUserRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(user)
                        .accountNumber("1000000013").build());

        //when
        AccountDto accountDto = accountService.createAccount(1L, 1000000012L);

        //then
        assertEquals(12L, accountDto.getUserId());
        assertEquals("1000000013", accountDto.getAccountNumber());

    }
}