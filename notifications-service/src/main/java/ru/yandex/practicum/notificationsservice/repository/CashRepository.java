package ru.yandex.practicum.notificationsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.notificationsservice.entity.Cash;

@Repository
public interface CashRepository extends CrudRepository<Cash, Long> {
}