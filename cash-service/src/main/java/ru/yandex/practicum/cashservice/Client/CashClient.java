package ru.yandex.practicum.cashservice.Client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.yandex.practicum.cashservice.dto.CashDto;
import ru.yandex.practicum.cashservice.dto.CashResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class CashClient {
    private final WebClient WebClient;

    public CashResponse execute(CashDto cash) {
        return WebClient
                .post()
                .uri("account-ingress:8081" + "/account/cash")
                .bodyValue(cash)
                .retrieve()
                .bodyToMono(CashResponse.class)
                .block();
    }
}