package ru.yandex.practicum.accountsservice.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.accountsservice.dto.Response;
import ru.yandex.practicum.accountsservice.entity.Account;

@Component(value = "accRespMapper")
public class AccountResponseDtoMapper implements IMapper<Account, Response> {
    @Override
    public Response map(Account from) {
        return Response.builder()
                .amount(from.getBalance())
                .birthdate(from.getBirthday())
                .name(from.getName())
                .build();
    }
}
