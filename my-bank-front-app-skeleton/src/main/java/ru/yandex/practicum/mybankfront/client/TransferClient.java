package ru.yandex.practicum.mybankfront.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.client.RestTemplate;
import ru.yandex.practicum.mybankfront.exception.OAuth2AccessTokenException;

import java.net.URI;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class TransferClient {
    private final RestTemplate restTemplate;

    @Value("${gateway.base-url}")
    private final String baseUrl;

    public void submitToGateway() {
        var accessToken = getAccessTokenHolder();
        log.debug(getAccessTokenHolder());
        if (Objects.nonNull(accessToken)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            RequestEntity<?> requestEntity = new RequestEntity<>(null, headers, HttpMethod.POST, URI.create(baseUrl + "/accounts"));

            log.info("Starting sent ");
            restTemplate.exchange(requestEntity, String.class);
        } else {
            throw new OAuth2AccessTokenException("AccessToken is null. Cannot submit to gateway.");
        }

    }

    private String getAccessTokenHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
            log.debug("User JWT has been found");
            return oauth2Token.getName();
        }

        log.debug("User JWT is null");
        return null;
    }
}
