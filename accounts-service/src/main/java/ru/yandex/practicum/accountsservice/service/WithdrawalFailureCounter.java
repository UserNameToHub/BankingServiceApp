package ru.yandex.practicum.accountsservice.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WithdrawalFailureCounter {

    private final MeterRegistry meterRegistry;

    public void increment(String username) {
        Counter counter = meterRegistry.counter("withdrawal.failure.count", "username", username);
        counter.increment();
    }

    public void increment(String senderUsername, String recipientUsername) {
        Counter counter = meterRegistry.counter("withdrawal.failure.count", "username", senderUsername, "recipient", recipientUsername);
        counter.increment();
    }
}