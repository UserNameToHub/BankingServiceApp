package ru.yandex.ptracticum.transferservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.yandex.ptracticum.transferservice.exception.DiscoveryException;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;
import ru.yandex.ptracticum.transferservice.dto.TransferResponse;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferClient {
    private final WebClient WebClient;
    private final DiscoveryClient discoveryClient;

    public TransferResponse transfer(TransferDto transferDto) {
        String accountHost = discover("accounts-service");
        transferNotification(transferDto);
        return WebClient
                .post()
                .uri(accountHost + "/account/transfer")
                .bodyValue(transferDto)
                .retrieve()
                .bodyToMono(TransferResponse.class)
                .block();
    }

    private void transferNotification(TransferDto transferDto) {
        String host = discover("notification-service");
        WebClient
            .post()
            .uri(host + "/notifications/transfer")
            .bodyValue(transferDto)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    private String discover(String service) {
        List<ServiceInstance> instances = discoveryClient.getInstances(service);
        if (!instances.isEmpty()) {
            ServiceInstance instance = instances.get(0);
            return instance.getHost();
        } else {
            throw new DiscoveryException("Server is unavailable");
        }
    }
}