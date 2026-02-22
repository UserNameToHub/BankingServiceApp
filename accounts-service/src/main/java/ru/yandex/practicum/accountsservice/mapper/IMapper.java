package ru.yandex.practicum.accountsservice.mapper;

import ru.yandex.practicum.accountsservice.entity.Account;

import java.util.function.Function;

/**
 *
 * @param <T1> - from
 * @param <T2> - to
 */

@FunctionalInterface
public interface IMapper<T1, T2> {
    T2 map(T1 from);
}
