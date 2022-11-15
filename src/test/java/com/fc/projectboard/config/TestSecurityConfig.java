package com.fc.projectboard.config;

import com.fc.projectboard.domain.UserAccount;
import com.fc.projectboard.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod //각 테스트메소드가 실행되기 전에 코드를 수행..
    public void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "aufsprit",
                "pw",
                "aufspritTest-test@email.com",
                "aufspritTest-test",
                "test memo"
        )));
    }

}
