package ru.yandex.practicum.accountsservice.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.accountsservice.dto.CashAction;
import ru.yandex.practicum.accountsservice.dto.CashDto;
import ru.yandex.practicum.accountsservice.dto.TransferDto;

import java.time.LocalDate;

@RestController
@RequestMapping("/account")
public class MainController {
    @PostMapping("cash")
    public String editCash(@RequestBody CashDto action) {

    }

    @PostMapping("/transfer")
    public String executeTransfer(@RequestMapping TransferDto transferDto) {

    }

    @GetMapping
    public String account() {

    }
    @PostMapping
    public String editAccount(@RequestParam("name") String name,
                              @RequestParam("birthdate") LocalDate birthdate) {

    }
}