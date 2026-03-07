package ru.yandex.ptracticum.transferservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;
import ru.yandex.ptracticum.transferservice.dto.TransferResponse;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferClient {
    private final WebClient WebClient;

    public TransferResponse transfer(TransferDto transferDto) {
        return WebClient
                .post()
                .uri("account-ingress:8081" + "/account/transfer")
                .bodyValue(transferDto)
                .retrieve()
                .bodyToMono(TransferResponse.class)
                .block();
    }
}