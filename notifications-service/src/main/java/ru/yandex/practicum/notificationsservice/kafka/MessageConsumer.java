package ru.yandex.practicum.notificationsservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    @KafkaListener(topics = "account-events")
    public void printAccountEvents(ConsumerRecord<?, ?> record) {
        log.info("Получено сообщение из account-events: ключ [{}], значение [{}]", record.key(), record.value());
    }

    @KafkaListener(topics = "cash-events")
    public void printCashEvents(ConsumerRecord<?, ?> record) {
        log.info("Получено сообщение из cash-events: ключ [{}], значение [{}]", record.key(), record.value());
    }

    @KafkaListener(topics = "transfer-events")
    public void TransferEvents(ConsumerRecord<?, ?> record) {
        log.info("Получено сообщение из transfer-events: ключ [{}], значение [{}]", record.key(), record.value());
    }
}