package com.yalovchuk.transfer.client

import com.yalovchuk.transfer.client.dto.AccountDto
import spock.lang.Specification

class AccountClientImplTest extends Specification {

    def accountClient = new AccountClientImpl()

    def "should return account"() {
        given:
        AccountDto expected = new AccountDto(1, 10)

        when:
        AccountDto actual = accountClient.get(1)

        then:
        actual == expected
    }

    def "should update account"() {
        given:
        AccountDto input = new AccountDto(1, 50)

        when:
        AccountDto actual = accountClient.update(input)

        then:
        actual == input
    }
}
