package ru.yandex.practicum.accountsservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.accountsservice.dto.*;
import ru.yandex.practicum.accountsservice.entity.Account;
import ru.yandex.practicum.accountsservice.exception.NoAccountException;
import ru.yandex.practicum.accountsservice.mapper.IMapper;
import ru.yandex.practicum.accountsservice.repository.AccountRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.accountsservice.util.Constant.*;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    @Qualifier(value = "accMapper")
    private final IMapper accountDtoMapper;
    @Qualifier(value = "accRespMapper")
    private final IMapper accountResponseDtoMapper;
    private final AccountRepository accountRepository;
    private IResponseMessage response;

    @Transactional
    public IResponseMessage editCash(CashDto dto) throws NoAccountException {
        Account account = getAccount(dto.login());

        switch (dto.action()) {
            case GET -> {
                if (isValidTransaction(account.getBalance(), dto.value())) {
                    account.setBalance(account.getBalance() + dto.value());
                    Account savedAccount = accountRepository.save(account);

                    ResponseShort preResponse = ResponseShort.builder()
                            .amount(savedAccount.getBalance())
                            .info(WITHDRAWAL_SUCCESSFUL + " " + "Снято %d руб".formatted(dto.value()))
                            .error(null)
                            .build();

                    response = preResponse;
                } else {
                    ResponseShort preResponse = ResponseShort.builder()
                            .amount(account.getBalance())
                            .error(INSUFFICIENT_FUNDS)
                            .info(null)
                            .build();
                    response = preResponse;
                }
            }
            default -> {
                account.setBalance(dto.value());
                accountRepository.save(account);
                ResponseShort preResponse = ResponseShort.builder()
                        .amount(account.getBalance())
                        .info(WITHDRAWAL_SUCCESSFUL + " " + "Снято %d руб".formatted(dto.value()))
                        .error(null)
                        .build();
                response = preResponse;
            }
        }

        return response;
    }

    @Transactional
    public IResponseMessage makeTransfer(TransferDto transferDto) {
       Account insufficient = getAccount(transferDto.from());
       Account recipient = getAccount(transferDto.to());

       if (isValidTransaction(insufficient.getBalance(), transferDto.value())) {
           insufficient.setBalance(insufficient.getBalance() - transferDto.value());
           recipient.setBalance(recipient.getBalance() + transferDto.value());
           accountRepository.save(insufficient);
           accountRepository.save(recipient);

           ResponseShort preResponse = ResponseShort.builder()
                   .amount(insufficient.getBalance())
                   .info(TRANSACTION_SUCCESSFUL + " " + "Успешно переведено %d руб клиенту %s")
                   .error(null)
                   .build();

           response = preResponse;
       } else {
           log.warn("The transfer operation failed due to insufficient funds in the account");
           ResponseShort preResponse = ResponseShort.builder()
                   .amount(insufficient.getBalance())
                   .error(TRANSACTION_FAILED + ". " + INSUFFICIENT_FUNDS)
                   .info(null)
                   .build();

           response = preResponse;
       }

       return response;
    }

    public IResponseMessage get(String login) {
        Account account = getAccount(login);
        List<Account> accounts = (ArrayList<Account>) accountRepository.findAll();
        List<AccountDto> accountDtos = accounts.stream()
                .filter(acc -> !acc.equals(login))
                .map(acc -> (AccountDto) accountDtoMapper.map(acc))
                .toList();

        Response preResponse = (Response) accountResponseDtoMapper.map(account);
        preResponse.setAccounts(accountDtos);
        response = preResponse;

        return response;
    }

    public IResponseMessage editAccount(String login, String name, LocalDate birthdate) {
        Account account = getAccount(login);
        account.setName(name);
        account.setBirthday(birthdate);
        Account savedAccount = accountRepository.save(account);

        Response preResponse = (Response) accountResponseDtoMapper.map(savedAccount);
        response = preResponse;

        return response;
    }

    private Boolean isValidTransaction(@NonNull int baseValue, @NonNull int withdrawaValue) {
        return baseValue - withdrawaValue >= 0;
    }

    private Account getAccount(@NonNull String login) throws NoAccountException {
        return accountRepository
                .findByLogin(login)
                .orElseThrow(() -> new NoAccountException(USER_NOT_FOUND));
    }
}