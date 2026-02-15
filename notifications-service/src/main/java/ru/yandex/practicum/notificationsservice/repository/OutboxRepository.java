package ru.yandex.practicum.notificationsservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.notificationsservice.dto.Microservice;
import ru.yandex.practicum.notificationsservice.entity.CreateOutbox;

@Repository
public interface OutboxRepository extends CrudRepository<CreateOutbox, Long> {
    Page<CreateOutbox> findAllByMicroservice(Microservice microSrv, Pageable page);
}