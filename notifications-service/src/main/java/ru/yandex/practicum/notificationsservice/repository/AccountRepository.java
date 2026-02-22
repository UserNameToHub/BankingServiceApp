package ru.yandex.practicum.notificationsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.notificationsservice.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}