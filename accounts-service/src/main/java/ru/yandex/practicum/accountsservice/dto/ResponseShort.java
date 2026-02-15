package ru.yandex.practicum.accountsservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseShort implements IResponseMessage {
    private String error;
    private String info;
    private int amount;
}
