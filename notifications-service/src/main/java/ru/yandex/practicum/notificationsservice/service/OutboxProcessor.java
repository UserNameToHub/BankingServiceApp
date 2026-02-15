package ru.yandex.practicum.notificationsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.notificationsservice.dto.MicroserviceType;
import ru.yandex.practicum.notificationsservice.entity.Account;
import ru.yandex.practicum.notificationsservice.entity.Cash;
import ru.yandex.practicum.notificationsservice.entity.CreateOutbox;
import ru.yandex.practicum.notificationsservice.entity.Transfer;
import ru.yandex.practicum.notificationsservice.repository.AccountRepository;
import ru.yandex.practicum.notificationsservice.repository.CashRepository;
import ru.yandex.practicum.notificationsservice.repository.OutboxRepository;
import ru.yandex.practicum.notificationsservice.repository.TransferRepository;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableRabbit
@RequiredArgsConstructor
public class OutboxProcessor {
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    private final RabbitTemplate rabbitTemplate;
    @Qualifier("accountBind")
    private final Binding accountBind;
    @Qualifier("cashBind")
    private final Binding cashBind;
    @Qualifier("transferBind")
    private final Binding transferBind;
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final CashRepository cashRepository;
    @Value(value = "${app.rmq-limit}")
    private final int limit = 5;

    @Scheduled(fixedDelayString = "PT1s")
    public void process() throws AmqpException {
        preProcess(accountRepository, MicroserviceType.ACCOUNT, accountBind, Account.class);
        preProcess(transferRepository, MicroserviceType.TRANSFER, transferBind, Transfer.class);
        preProcess(cashRepository, MicroserviceType.CASH, cashBind, Cash.class);
    }

    private <T> void preProcess(CrudRepository repository, MicroserviceType microSrv, Binding bind, Class<T> tClass) {
        Page<CreateOutbox> outboxEntries = outboxRepository.findAllByMicroservice(microSrv, Pageable.ofSize(limit));
        List<T> objects = (List<T>) repository.findAllById(outboxEntries.stream()
                .filter(item -> item.getMicroserviceType().equals(microSrv))
                .map(CreateOutbox::getEntityId)
                .collect(Collectors.toList()));

        for (T obj: objects) {
            byte[] raw = objectMapper.writeValueAsBytes(obj);
            try {
                rabbitTemplate.convertAndSend(bind.getRoutingKey(), raw);
            } catch (AmqpException ex) {
                throw new AmqpException(ex.getMessage());
            }
        }

        List<Long> processedIds = outboxEntries.stream()
                .map(CreateOutbox::getId)
                .toList();
        outboxRepository.deleteAllById(processedIds);
    }
}