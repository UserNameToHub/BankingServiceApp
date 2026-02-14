package ru.yandex.practicum.notificationsservice.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@Table(name = "transfers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transfer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromUser;
    private String toUser;
    private int amount;
    private LocalDate createAt;
}