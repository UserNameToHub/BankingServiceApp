package ru.yandex.ptracticum.transferservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.ptracticum.transferservice.dto.TransferDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    @Value("${topic.name}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(TransferDto cashDto) {
        try {
            log.info("Sending message to kafka");
            kafkaTemplate.send(topic,
                    "transfer",
                    cashDto.toString()
            );
        } catch (Exception e) {
            log.error("Error sending " + topic, e);
        }
    }
}