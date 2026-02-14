package ru.yandex.practicum.notificationsservice.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.notificationsservice.dto.CashAction;

import java.time.LocalDate;

@RestController("/notifications")
public class MainController {
    @PostMapping("accounts")
    public void edit(@RequestParam String username,
                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate birthday) {

    }


    @PostMapping("cash")
    public void action(@RequestParam String username,
                       @RequestParam CashAction action,
                       @RequestParam int amount) {

    }


    @PostMapping("/transfers")
    public void transfer(@RequestParam String fromUser,
                         @RequestParam String toUser,
                         @RequestParam int amount) {

    }
}
