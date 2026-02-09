package ru.yandex.practicum.mybankfront.exception;

public class OAuth2AccessTokenException extends RuntimeException {
    public OAuth2AccessTokenException(String message) {
        super(message);
    }
}
