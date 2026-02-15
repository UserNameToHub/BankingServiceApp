package ru.yandex.practicum.accountsservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.accountsservice.dto.CashDto;
import ru.yandex.practicum.accountsservice.dto.TransferDto;
import ru.yandex.practicum.accountsservice.entity.Account;
import ru.yandex.practicum.accountsservice.exception.NoAccountException;
import ru.yandex.practicum.accountsservice.repository.AccountRepository;
import static ru.yandex.practicum.accountsservice.util.Constant.*;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    private String message = USER_NOT_FOUND;

    @Transactional
    public String editCash(CashDto dto) throws NoAccountException {
        Account account = getAccount(dto.login());

        switch (dto.action()) {
            case GET -> {
                if (isValidTransaction(account.getBalance(), dto.value())) {
                    account.setBalance(account.getBalance() + dto.value());
                    accountRepository.save(account);
                } else {
                    message = INSUFFICIENT_FUNDS;
                }
            }
            default -> {
                account.setBalance(dto.value());
                accountRepository.save(account);
                message = WITHDRAWAL_SUCCESSFUL;
            }
        }

        return message;
    }

    @Transactional
    public String makeTransfer(TransferDto transferDto) {
       Account insufficient = getAccount(transferDto.from());
       Account recipient = getAccount(transferDto.to());

       if (isValidTransaction(insufficient.getBalance(), transferDto.value())) {
           insufficient.setBalance(insufficient.getBalance() - transferDto.value());
           recipient.setBalance(recipient.getBalance() + transferDto.value());
           accountRepository.save(insufficient);
           accountRepository.save(recipient);

           message = TRANSACTION_SUCCESSFUL;
       } else {
           log.warn("The transfer operation failed due to insufficient funds in the account");
           message = TRANSACTION_FAILED + ". " + INSUFFICIENT_FUNDS;
       }

       return message;
    }

    private Boolean isValidTransaction(@NonNull int baseValue, @NonNull int withdrawaValue) {
        return baseValue - withdrawaValue >= 0;
    }

    private Account getAccount(@NonNull String login) throws NoAccountException {
        return accountRepository
                .findByLogin(login)
                .orElseThrow(() -> new NoAccountException(message));
    }
}
