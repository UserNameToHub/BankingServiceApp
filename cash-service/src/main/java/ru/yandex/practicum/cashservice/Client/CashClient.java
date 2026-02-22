package ru.yandex.practicum.cashservice.Client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.cashservice.dto.CashDto;
import ru.yandex.practicum.cashservice.dto.CashResponse;
import ru.yandex.practicum.cashservice.exception.DiscoveryException;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CashClient {
    private final WebClient WebClient;
    private final DiscoveryClient discoveryClient;

    public CashResponse execute(CashDto cash) {
        String host = discover("account-service");
        transferNotification(cash);
        return WebClient
                .post()
                .uri(host + "/account/cash")
                .bodyValue(cash)
                .retrieve()
                .bodyToMono(CashResponse.class)
                .block();
    }

    private void transferNotification(CashDto cash) {
        String accountHost = discover("notification-service");
        WebClient
                .post()
                .uri(accountHost + "/cash")
                .bodyValue(cash)
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