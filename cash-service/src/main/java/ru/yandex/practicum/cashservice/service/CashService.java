package ru.yandex.practicum.cashservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.cashservice.Client.AccountClient;
import ru.yandex.practicum.cashservice.dto.CashDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class CashService {
    private final AccountClient client;

    public String performMoneyTransfer(CashDto cash) {
        log.info("A cash request for {}, amount {}",  cash.value(), getAuthName());
        String result = client.execute(cash);
        log.info("Successful cash: {}", result);
        return result;
    }

    private String getAuthName() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
