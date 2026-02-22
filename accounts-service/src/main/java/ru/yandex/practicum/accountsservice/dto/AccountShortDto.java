package ru.yandex.practicum.accountsservice.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AccountShortDto(String username, LocalDate birthday) {
}
