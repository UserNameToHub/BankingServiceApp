package ru.yandex.practicum.notificationsservice.dto;

import ru.yandex.practicum.notificationsservice.dto.enumiration.CashAction;

public record CashDto(String username, CashAction action, int amount) {
}
