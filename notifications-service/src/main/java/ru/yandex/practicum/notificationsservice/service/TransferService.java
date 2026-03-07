package ru.yandex.practicum.notificationsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.notificationsservice.dto.enumiration.MicroserviceType;
import ru.yandex.practicum.notificationsservice.dto.TransferDto;
import ru.yandex.practicum.notificationsservice.entity.CreateOutbox;
import ru.yandex.practicum.notificationsservice.entity.Transfer;
import ru.yandex.practicum.notificationsservice.repository.OutboxRepository;
import ru.yandex.practicum.notificationsservice.repository.TransferRepository;

import java.time.LocalDate;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransferService {
//    private final TransferRepository transferRepository;
//    private final OutboxRepository outboxRepository;
//
//    @Transactional
//    public Transfer create(TransferDto transferDto) {
//        log.debug("Starting create transfer from {}, to {}, amount {}",
//                transferDto.fromUser(), transferDto.toUser(), transferDto.amount());
//        Transfer transfer = Transfer.builder()
//                .createAt(LocalDate.now())
//                .fromUser(transferDto.fromUser())
//                .toUser(transferDto.toUser())
//                .amount(transferDto.amount())
//                .build();
//        Transfer savedTransfer = transferRepository.save(transfer);
//
//        log.debug("Starting create createOutbox");
//        CreateOutbox createOutbox = CreateOutbox.builder()
//                .entityId(savedTransfer.getId())
//                .microserviceType(MicroserviceType.TRANSFER)
//                .build();
//        outboxRepository.save(createOutbox);
//        return savedTransfer;
//    }
}