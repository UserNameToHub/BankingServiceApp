package ru.yandex.practicum.mybankfront.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String name;
    private LocalDate birthdate;
    private int amount;
    private List<AccountResponseShort> accounts;
    private String error;
    private String info;
}
