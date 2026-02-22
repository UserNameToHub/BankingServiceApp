package ru.yandex.practicum.mybankfront.client;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TransferClient {
    private final WebClient gatewayWebClient;

    public <T> T submit2GatewayPOST(String uri, Map<String, String> params, Class<T> tClass) {
        return gatewayWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .queryParams(convertMap2MultiValMap(params))
                        .build())
                .retrieve()
                .bodyToMono(tClass)
                .block(); // Use Sync
    }

    public <T> T submit2GatewayGET(String uri, Map<String, String> params, Class<T> tClass) {
        return gatewayWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .queryParams(convertMap2MultiValMap(params))
                        .build())
                .retrieve()
                .bodyToMono(tClass)
                .block(); // Use Sync
    }

    private MultiValueMap<String, String> convertMap2MultiValMap(Map<String, String> origMap) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

        for (Map.Entry<String, String> entry: origMap.entrySet()) {
            multiValueMap.add(entry.getKey(), entry.getValue());
        }

        return multiValueMap;
    }
}