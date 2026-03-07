package ru.yandex.practicum.accountsservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.accountsservice.dto.AccountShortDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    @Value("${topic.name}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(AccountShortDto accountShortDto) {
        try {
            log.info("Sending message to kafka");
            kafkaTemplate.send(topic,
                    "account",
                    accountShortDto.toString()
            );
        } catch (Exception e) {
            log.error("Error sending " + topic, e);
        }
    }
}