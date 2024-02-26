package org.example.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.account.controller.AccountController;
import org.example.account.dto.AccountDto;
import org.example.account.dto.CreateAccount;
import org.example.account.service.AccountService;
import org.example.account.service.RedisTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(AccountController.class)
class AccountApplicationTests {

    @MockBean
    private AccountService accountService;

    @MockBean
    private RedisTestService redisTestService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void successCreateAccount() throws Exception {
        //given
        given(accountService.createAccount(anyLong(), anyLong()))
                .willReturn(AccountDto.builder()
                        .userId(1L)
                        .accountNumber("1234567890")
                        .registeredAt(LocalDateTime.now())
                        .unRegisteredAt(LocalDateTime.now())
                        .build());
        //when
        //then
        mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new CreateAccount.Request(3333L, 1111L)
                )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andDo(print());
    }


    @Test
    void successGetAccount() throws Exception {
        //given

        //when

        //then
    }

}
