package ru.yandex.ptracticum.transferservice.dto;

import lombok.Builder;

public record TransferDto(int value, String fromLogin, String toLogin) {
}
