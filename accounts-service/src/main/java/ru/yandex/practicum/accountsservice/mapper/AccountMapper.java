package ru.yandex.practicum.accountsservice.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.accountsservice.dto.AccountDto;
import ru.yandex.practicum.accountsservice.entity.Account;

@Component(value = "accMapper")
public class AccountMapper implements IMapper<Account, AccountDto> {
    @Override
    public AccountDto map(Account from) {
        return AccountDto.builder()
                .name(from.getName())
                .birthday(from.getBirthday())
                .balance(from.getBalance())
                .build();
    }
}
