package com.yalovchuk.transfer.component;

import com.google.inject.Singleton;
import com.yalovchuk.transfer.client.AccountClient;
import com.yalovchuk.transfer.client.dto.AccountDto;
import com.yalovchuk.transfer.dao.TransferDao;
import com.yalovchuk.transfer.model.Status;
import com.yalovchuk.transfer.model.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class TransferHandlerImpl implements TransferHandler {

    private final AccountClient accountClient;
    private final TransferDao transferDao;

    @Override
    public void handle(Transfer transfer) {
        if (transfer.getStatus() == Status.CREATED) {
            log.info("Processing transfer {}", transfer.getId());
            updateTransfer(transfer, Status.PROCESSING);
            AccountDto fromAccount = accountClient.get(transfer.getFromAccountId());
            AccountDto toAccount = accountClient.get(transfer.getToAccountId());
            if (isInsufficientBalance(fromAccount, transfer)) {
                updateTransfer(transfer, Status.REJECTED);
            } else {
                executeTransfer(transfer, fromAccount, toAccount);
            }
        }
    }

    private void updateTransfer(Transfer transfer, Status status) {
        transfer.setStatus(status);
        transferDao.update(transfer);
    }

    private boolean isInsufficientBalance(AccountDto fromAccount, Transfer transfer) {
        return fromAccount.getValue() - transfer.getValue() < 0;
    }

    @SuppressWarnings("all")
    private void executeTransfer(Transfer transfer, AccountDto fromAccount, AccountDto toAccount) {
        boolean fromAccountIdLessThanToAccountId = fromAccount.getId() < toAccount.getId();
        AccountDto firstToLock = fromAccountIdLessThanToAccountId ? fromAccount : toAccount;
        AccountDto secondToLock = fromAccountIdLessThanToAccountId ? toAccount : fromAccount;
        synchronized (firstToLock) {
            synchronized (secondToLock) {
                if (isInsufficientBalance(fromAccount, transfer)) {
                    updateTransfer(transfer, Status.REJECTED);
                } else {
                    updateAccount(fromAccount, -transfer.getValue());
                    updateAccount(toAccount, transfer.getValue());
                    updateTransfer(transfer, Status.APPROVED);
                }
            }
        }
    }

    private void updateAccount(AccountDto accountDto, int delta) {
        accountDto.setValue(accountDto.getValue() + delta);
        accountClient.update(accountDto);
    }
}