package com.yalovchuk.transfer.client;

import com.yalovchuk.transfer.client.dto.AccountDto;

public interface AccountClient {

    AccountDto get(int id);

    AccountDto update(AccountDto accountDto);
}
