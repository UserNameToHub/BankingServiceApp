package ru.yandex.practicum.notificationsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.notificationsservice.dto.AccountDro;
import ru.yandex.practicum.notificationsservice.dto.enumiration.MicroserviceType;
import ru.yandex.practicum.notificationsservice.entity.Account;
import ru.yandex.practicum.notificationsservice.entity.CreateOutbox;
import ru.yandex.practicum.notificationsservice.repository.AccountRepository;
import ru.yandex.practicum.notificationsservice.repository.OutboxRepository;

import java.time.LocalDate;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
//    private final AccountRepository accountRepository;
//    private final OutboxRepository outboxRepository;
//
//    @Transactional
//    public Account create(AccountDro accountDro) {
//        log.debug("Starting create account for {}", accountDro.username());
//        Account account = Account.builder()
//                .createAt(LocalDate.now())
//                .userName(accountDro.username())
//                .birthday(accountDro.birthday())
//                .build();
//        Account savedAccount = accountRepository.save(account);
//
//        log.debug("Starting create createOutbox for {}", accountDro.username());
//        CreateOutbox createOutbox = CreateOutbox.builder()
//                .entityId(savedAccount.getId())
//                .microserviceType(MicroserviceType.ACCOUNT)
//                .build();
//        outboxRepository.save(createOutbox);
//        return savedAccount;
//    }
}
