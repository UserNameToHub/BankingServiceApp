package ru.yandex.practicum.notificationsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.yandex.practicum.notificationsservice.dto.MicroserviceType;

@Data
@Table(name = "create_outbox")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CreateOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long entityId;
    @Enumerated(EnumType.STRING)
    private MicroserviceType microserviceType;
}