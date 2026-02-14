package ru.yandex.practicum.notificationsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.notificationsservice.repository.CashRepository;
import ru.yandex.practicum.notificationsservice.repository.OutboxRepository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OutboxProcessor {
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    private final Repository repository;
    private final RabbitTemplate rabbitTemplate;



    @Scheduled(fixedDelayString = "PT1s") // Обрабатываем таблицу Outbox каждую секунду
    public void process() throws IOException, InterruptedException {
        Page<OrderCreateOutbox> outboxEntries = outboxRepository.findAll(Pageable.ofSize(limit));
        List<Order> orders = orderRepository.findAllById(outboxEntries.map(OrderCreateOutbox::getOrderId));

        rabbitTemplate.send();
        // Подключаемся к NATS только на время отправки сообщений
        try (Connection natsConnection = Nats.connect(natsConnectionUrl)) {
            for (Order order : orders) {
                // Преобразуем заказы в JSON-формат
                byte[] orderRaw = objectMapper.writeValueAsBytes(order);

                // Отправляем данные в брокер
                natsConnection.publish(topicName, orderRaw);
            }
        }

        // Удаляем обработанные записи
        List<Long> processedIds = outboxEntries.stream()
                .map(OrderCreateOutbox::getId)
                .toList();
        outboxRepository.deleteAllById(processedIds);
    }

}
