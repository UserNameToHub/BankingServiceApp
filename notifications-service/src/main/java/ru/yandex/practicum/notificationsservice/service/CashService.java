package ru.yandex.practicum.notificationsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.notificationsservice.dto.CashDto;
import ru.yandex.practicum.notificationsservice.dto.enumiration.MicroserviceType;
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
//    private final CashRepository cashRepository;
//    private final OutboxRepository outboxRepository;
//
//    @Transactional
//    public Cash create(CashDto cashDto) {
//        log.debug("Starting create cash for {}", cashDto.username());
//        Cash cash = Cash.builder()
//                .createAt(LocalDate.now())
//                .amount(cashDto.amount())
//                .action(cashDto.action())
//                .build();
//        Cash savedCash = cashRepository.save(cash);
//
//        log.debug("Starting create createOutbox for {}", cashDto.username());
//        CreateOutbox createOutbox = CreateOutbox.builder()
//                .entityId(savedCash.getId())
//                .microserviceType(MicroserviceType.CASH)
//                .build();
//        outboxRepository.save(createOutbox);
//        return savedCash;
//    }
}