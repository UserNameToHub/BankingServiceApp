package ru.yandex.practicum.notificationsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.notificationsservice.entity.CreateOutbox;

@Repository
public interface OutboxRepository extends CrudRepository<CreateOutbox, Long> {
}