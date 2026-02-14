package ru.yandex.ptracticum.transferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.ptracticum.transferservice.client.AccountClient;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferService {
    private final AccountClient client;

    public String transfer(TransferDto transferDto) {
        log.info("A transfer request for user {}, amount {}", transferDto.login(), transferDto.value());
        String result = client.transfer(transferDto);
        log.info("Successful transfer: {}", result);
        return result;
    }
}
