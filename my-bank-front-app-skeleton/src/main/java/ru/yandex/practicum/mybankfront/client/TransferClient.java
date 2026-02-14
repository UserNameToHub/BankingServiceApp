package ru.yandex.practicum.mybankfront.client;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class TransferClient {

    private final WebClient gatewayWebClient;
    private final String gatewayBaseUrl;

    public TransferClient(WebClient gatewayWebClient,
                          @Value("${gateway.base-url}") String gatewayBaseUrl) {
        this.gatewayWebClient = gatewayWebClient;
        this.gatewayBaseUrl = gatewayBaseUrl;
    }

    public String submitToGateway(String url, @Nullable Map<String, String> params) {
        return gatewayWebClient
                .get()
                .uri(gatewayBaseUrl + url)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Use Sync
    }
}