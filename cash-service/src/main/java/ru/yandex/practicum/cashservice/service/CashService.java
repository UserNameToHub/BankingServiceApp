package ru.yandex.practicum.cashservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.cashservice.Client.CashClient;
import ru.yandex.practicum.cashservice.dto.CashAction;
import ru.yandex.practicum.cashservice.dto.CashDto;
import ru.yandex.practicum.cashservice.dto.CashResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class CashService {
    private final CashClient client;

    public CashResponse performMoneyTransfer(int value, CashAction action) {
        CashDto cashDto = new CashDto(value, action, getAuthName());
        log.info("A cash request for {}, amount {}, type {}", cashDto.login(),  cashDto.value(), cashDto.action());
        CashResponse result = client.execute(cashDto);
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
