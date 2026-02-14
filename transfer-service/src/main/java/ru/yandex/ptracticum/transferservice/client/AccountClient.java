package ru.yandex.ptracticum.transferservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountClient {
    private final WebClient accountsWebClient;

    public String transfer(TransferDto transferDto) {
        return accountsWebClient
                .post()
                .uri("/accounts/cash")
                .bodyValue(transferDto)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(throwable -> Mono.just("Ошибка при обращении к accounts-service: " + throwable.getMessage()))
                .block();
    }
}
