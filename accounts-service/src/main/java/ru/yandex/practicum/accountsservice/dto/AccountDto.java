package ru.yandex.practicum.accountsservice.dto;

import java.time.LocalDate;

public record AccountDto(String name, LocalDate birthday) {
}
