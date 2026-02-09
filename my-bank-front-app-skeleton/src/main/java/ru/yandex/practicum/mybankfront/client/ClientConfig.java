package ru.yandex.practicum.mybankfront.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {
    @Value("${gateway.base-url}")
    private String url;

    @Bean
    RestTemplate gatewayRestTemplate() {
        return new RestTemplate();
//        Builder()
//                .rootUri(url)
//                .build() ;
    }

    @Bean
    public TransferClient transferClient(RestTemplate restTemplate) {
        return new TransferClient(restTemplate, url);
    }
}
