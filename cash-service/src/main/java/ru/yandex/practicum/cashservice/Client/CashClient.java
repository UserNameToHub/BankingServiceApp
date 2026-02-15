package ru.yandex.practicum.cashservice.Client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.cashservice.dto.CashDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class CashClient {
    private final WebClient accountsWebClient;

    public String execute(CashDto cash) {
        return accountsWebClient
                .post()
                .uri("/accounts/cash")
                .bodyValue(cash)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(throwable -> Mono.just("Ошибка при обращении к accounts-service: " + throwable.getMessage()))
                .block();
    }
}
