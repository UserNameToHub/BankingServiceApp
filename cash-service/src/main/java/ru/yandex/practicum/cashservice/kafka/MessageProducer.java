package ru.yandex.practicum.cashservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.cashservice.dto.CashDto;
import ru.yandex.practicum.cashservice.service.NotificationFailureCounter;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    @Value("${topic.name}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NotificationFailureCounter counter;

    public void sendMessage(CashDto cashDto) {
        try {
            log.info("Sending message to kafka");
            kafkaTemplate.send(topic,
                    "cash",
                    cashDto.toString()
            );
        } catch (Exception e) {
            log.error("Error sending " + topic, e);
            counter.increment(cashDto.login());
        }
    }
}
