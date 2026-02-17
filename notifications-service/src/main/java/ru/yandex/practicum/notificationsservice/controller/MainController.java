package ru.yandex.practicum.notificationsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.notificationsservice.dto.AccountDro;
import ru.yandex.practicum.notificationsservice.dto.CashDto;
import ru.yandex.practicum.notificationsservice.dto.TransferDto;
import ru.yandex.practicum.notificationsservice.service.AccountService;
import ru.yandex.practicum.notificationsservice.service.CashService;
import ru.yandex.practicum.notificationsservice.service.TransferService;

@RestController("/notifications")
@RequiredArgsConstructor
public class MainController {
    private final TransferService transferService;
    private final CashService cashService;
    private final AccountService accountService;

    @PostMapping("account")
    public void edit(@RequestBody AccountDro accountDro) {
        accountService.create(accountDro);
    }

    @PostMapping("cash")
    public void action(CashDto cashDto) {
        cashService.create(cashDto);
    }

    @PostMapping("/transfer")
    public void transfer(TransferDto transferDto) {
        transferService.create(transferDto);
    }
}