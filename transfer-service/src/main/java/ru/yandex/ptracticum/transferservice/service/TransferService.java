package ru.yandex.ptracticum.transferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.yandex.ptracticum.transferservice.client.TransferClient;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;
import ru.yandex.ptracticum.transferservice.dto.TransferResponse;
import ru.yandex.ptracticum.transferservice.kafka.MessageProducer;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferService {
    private final TransferClient client;
    private final MessageProducer producer;

    public TransferResponse transfer(int value, String login) {
        String owner = SecurityContextHolder.getContext().getAuthentication().getName();
        TransferDto transferDto = new TransferDto(value, owner, login);
        log.info("A transfer request from {} to {}, amount {}",
                transferDto.fromLogin(), transferDto.toLogin(), transferDto.value());
        TransferResponse result = client.transfer(transferDto);
        producer.sendMessage(transferDto);
        log.info("Successful transfer: {}", result);
        return result;
    }
}