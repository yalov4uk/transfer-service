package com.yalovchuk.transfer.client;

import com.yalovchuk.transfer.client.dto.AccountDto;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class AccountClientImpl implements AccountClient {

    private final Map<Integer, AccountDto> accounts = List.of(
            new AccountDto(1, 10),
            new AccountDto(2, 10),
            new AccountDto(3, 20),
            new AccountDto(4, 20))
            .stream()
            .collect(Collectors.toMap(AccountDto::getId, Function.identity()));

    @Override
    public AccountDto get(int id) {
        return accounts.get(id);
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        AccountDto current = accounts.get(accountDto.getId());
        if (current != null) {
            current.setValue(accountDto.getValue());
            return current;
        }
        return null;
    }
}
