package com.yalovchuk.transfer.dao

import com.yalovchuk.transfer.model.Status
import com.yalovchuk.transfer.model.Transfer
import spock.lang.Specification

class TransferDaoImplTest extends Specification {

    def transferDao = new TransferDaoImpl()

    def "should put transfer to data store"() {
        given:
        Transfer input = new Transfer(null, 10, 1, 2, Status.CREATED)
        Transfer expected = new Transfer(2, 10, 1, 2, Status.CREATED)

        when:
        Transfer actual = transferDao.create(input)

        then:
        actual == expected
    }

    def "should return transfer from data store"() {
        given:
        Transfer expected = new Transfer(1, 10, 1, 2, Status.CREATED)

        when:
        Transfer actual = transferDao.get(1)

        then:
        actual == expected
    }
}
