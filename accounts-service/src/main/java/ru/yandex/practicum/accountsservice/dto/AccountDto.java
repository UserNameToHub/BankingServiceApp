package ru.yandex.practicum.accountsservice.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record AccountDto(String login, String name, LocalDate birthday, BigDecimal balance) {
}
