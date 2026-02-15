package ru.yandex.ptracticum.transferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.yandex.ptracticum.transferservice.client.AccountClient;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferService {
    private final AccountClient client;

    public String transfer(int value, String login) {
        String owner = SecurityContextHolder.getContext().getAuthentication().getName();
        TransferDto transferDto = new TransferDto(value, owner, login);
        log.info("A transfer request from {} to {}, amount {}",
                transferDto.fromLogin(), transferDto.toLogin(), transferDto.value());
        String result = client.transfer(transferDto);
        log.info("Successful transfer: {}", result);
        return result;
    }
}
