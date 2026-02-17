package ru.yandex.practicum.cashservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CashResponse {
    private String error;
    private String info;
    private int amount;
}
