package ru.yandex.practicum.accountsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.accountsservice.dto.*;
import ru.yandex.practicum.accountsservice.kafka.MessageProducer;
import ru.yandex.practicum.accountsservice.service.AccountService;

import java.time.LocalDate;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class MainController {
    private final AccountService accountService;
    private final MessageProducer producer;

    @PostMapping("cash")
    public IResponseMessage editCash(@RequestBody CashDto action) {
        return accountService.editCash(action);
    }

    @PostMapping("/transfer")
    public IResponseMessage executeTransfer(@RequestBody TransferDto transferDto) {
        return accountService.makeTransfer(transferDto);
    }

    @PostMapping
    public IResponseMessage editAccount(@RequestParam("login")String login,
                              @RequestParam("name") String name,
                              @RequestParam("birthdate") LocalDate birthdate) {
        producer.sendMessage(new AccountShortDto(name, birthdate));
        return accountService.editAccount(login, name, birthdate);
    }

    @GetMapping
    public IResponseMessage getAccount(String login) {
        return accountService.get(login);
    }
}