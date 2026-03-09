package ru.yandex.practicum.cashservice.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFailureCounter {
    private final MeterRegistry meterRegistry;

    public void increment(String username) {
        Counter counter = meterRegistry.counter("notification.failure.count", "username", username);
        counter.increment();
    }
}
