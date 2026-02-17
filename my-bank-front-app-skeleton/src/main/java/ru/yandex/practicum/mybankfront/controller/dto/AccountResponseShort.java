package ru.yandex.practicum.mybankfront.controller.dto;

import java.time.LocalDate;

public record AccountResponseShort(String name, LocalDate birthday, int balance) {
}
