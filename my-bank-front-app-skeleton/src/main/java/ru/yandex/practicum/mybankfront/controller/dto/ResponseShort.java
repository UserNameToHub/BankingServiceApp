package ru.yandex.practicum.mybankfront.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseShort {
    private String error;
    private String info;
    private int amount;
}
