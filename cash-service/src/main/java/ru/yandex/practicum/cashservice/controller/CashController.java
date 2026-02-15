package ru.yandex.practicum.cashservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.cashservice.dto.CashAction;
import ru.yandex.practicum.cashservice.dto.CashDto;
import ru.yandex.practicum.cashservice.service.CashService;

@RestController
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController {
    private final CashService service;

    @PostMapping
    public String edit(@RequestParam("value") int value,
                       @RequestParam("cashAction")CashAction action) {
        return service.performMoneyTransfer(value, action);
    }
}
