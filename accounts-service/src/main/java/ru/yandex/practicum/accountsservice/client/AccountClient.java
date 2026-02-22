package ru.yandex.practicum.accountsservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.accountsservice.dto.AccountShortDto;
import ru.yandex.practicum.accountsservice.exception.DiscoveryException;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountClient {
    private final org.springframework.web.reactive.function.client.WebClient WebClient;
    private final DiscoveryClient discoveryClient;
    public void transferNotification(AccountShortDto accountShortDto) {
        String accountHost = discover("notification-service");
        WebClient
                .post()
                .uri(accountHost + "/notifications/account")
                .bodyValue(accountShortDto)
                .retrieve()
                .bodyToMono(AccountShortDto.class)
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
