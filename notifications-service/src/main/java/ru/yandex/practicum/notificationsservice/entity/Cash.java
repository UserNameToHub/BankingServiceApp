package ru.yandex.practicum.notificationsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.yandex.practicum.notificationsservice.dto.CashAction;

import java.time.LocalDate;

@Data
@Table(name = "cash")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cash {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Enumerated(value = EnumType.STRING)
    private CashAction action;
    private int amount;
    private LocalDate createAt;
}