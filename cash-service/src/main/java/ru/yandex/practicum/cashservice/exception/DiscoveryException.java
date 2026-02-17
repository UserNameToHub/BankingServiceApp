package ru.yandex.practicum.cashservice.exception;

public class DiscoveryException extends RuntimeException {
    private String message;
    public DiscoveryException(String message) {
        super(message);
    }
}
