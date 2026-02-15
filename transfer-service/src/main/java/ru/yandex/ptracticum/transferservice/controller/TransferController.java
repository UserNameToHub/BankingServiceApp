package ru.yandex.ptracticum.transferservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;
import ru.yandex.ptracticum.transferservice.service.TransferService;

@Slf4j
@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService service;

    @PostMapping
    public String makeTransfer(@RequestParam("value") int value,
                               @RequestParam("login") String login) {
        return service.transfer(value, login);
    }
}
