package ru.yandex.practicum.notificationsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.notificationsservice.dto.CashAction;
import ru.yandex.practicum.notificationsservice.dto.MicroserviceType;
import ru.yandex.practicum.notificationsservice.entity.Cash;
import ru.yandex.practicum.notificationsservice.entity.CreateOutbox;
import ru.yandex.practicum.notificationsservice.repository.CashRepository;
import ru.yandex.practicum.notificationsservice.repository.OutboxRepository;

import java.time.LocalDate;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CashService {
    private final CashRepository cashRepository;
    private final OutboxRepository outboxRepository;

    @Transactional
    public Cash create(String username, CashAction action, int amount) {
        log.debug("Starting create cash for {}", username);
        Cash cash = Cash.builder()
                .createAt(LocalDate.now())
                .amount(amount)
                .action(action)
                .build();
        Cash savedCash = cashRepository.save(cash);

        log.debug("Starting create createOutbox for {}", username);
        CreateOutbox createOutbox = CreateOutbox.builder()
                .entityId(savedCash.getId())
                .microserviceType(MicroserviceType.CASH)
                .build();
        outboxRepository.save(createOutbox);
        return savedCash;
    }
}