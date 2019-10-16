package com.yalovchuk.transfer.component

import com.yalovchuk.transfer.client.AccountClient
import com.yalovchuk.transfer.client.dto.AccountDto
import com.yalovchuk.transfer.component.TransferHandlerImpl
import com.yalovchuk.transfer.dao.TransferDao
import com.yalovchuk.transfer.model.Status
import com.yalovchuk.transfer.model.Transfer
import spock.lang.Specification

class TransferHandlerImplTest extends Specification {

    AccountClient accountClient = Mock()
    TransferDao transferDao = Mock()

    def transferHandler = new TransferHandlerImpl(accountClient, transferDao)

    def "should approve transfer if sufficient balance"() {
        given:
        AccountDto fromAccount = new AccountDto(1, 20)
        AccountDto toAccount = new AccountDto(2, 5)
        Transfer input = new Transfer(1, 10, 1, 2, Status.CREATED)
        accountClient.get(1) >> fromAccount
        accountClient.get(2) >> toAccount

        when:
        transferHandler.handle(input)

        then:
        1 * transferDao.update(new Transfer(1, 10, 1, 2, Status.PROCESSING))
        1 * accountClient.update(new AccountDto(1, 10))
        1 * accountClient.update(new AccountDto(2, 15))
        1 * transferDao.update(new Transfer(1, 10, 1, 2, Status.APPROVED))
    }

    def "should reject transfer if insufficient balance"() {
        given:
        AccountDto fromAccount = new AccountDto(1, 5)
        AccountDto toAccount = new AccountDto(2, 5)
        Transfer input = new Transfer(1, 10, 1, 2, Status.CREATED)
        accountClient.get(1) >> fromAccount
        accountClient.get(2) >> toAccount

        when:
        transferHandler.handle(input)

        then:
        1 * transferDao.update(new Transfer(1, 10, 1, 2, Status.PROCESSING))
        1 * transferDao.update(new Transfer(1, 10, 1, 2, Status.REJECTED))
    }
}
