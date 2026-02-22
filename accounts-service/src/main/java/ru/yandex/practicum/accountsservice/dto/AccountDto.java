package ru.yandex.practicum.accountsservice.dto;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record AccountDto(String name, LocalDate birthday, int balance) {
}
