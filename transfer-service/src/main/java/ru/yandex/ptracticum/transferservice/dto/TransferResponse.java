package ru.yandex.ptracticum.transferservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponse {
    private String error;
    private String info;
    private int amount;
}
