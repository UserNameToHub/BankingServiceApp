package ru.yandex.practicum.notificationsservice.dto;

import org.springframework.web.bind.annotation.RequestParam;

public record TransferDto(String fromUser, String toUser, int amount) {
}
