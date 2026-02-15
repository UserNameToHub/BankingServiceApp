package ru.yandex.practicum.accountsservice.dto;

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
public class Response implements IResponseMessage{
    private String name;
    private LocalDate birthdate;
    private int amount;
    private List<AccountDto> accounts;
    private String error;
    private String info;
}
