package ru.yandex.practicum.mybankfront.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TransferClient {

    private final WebClient gatewayWebClient;
    private final String gatewayBaseUrl;

    public TransferClient(WebClient gatewayWebClient,
                          @Value("${gateway.base-url}") String gatewayBaseUrl) {
        this.gatewayWebClient = gatewayWebClient;
        this.gatewayBaseUrl = gatewayBaseUrl;
    }

    public String submitToGateway(String url) {
        return gatewayWebClient
                .get()
                .uri(gatewayBaseUrl + url)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Use Sync
    }
}