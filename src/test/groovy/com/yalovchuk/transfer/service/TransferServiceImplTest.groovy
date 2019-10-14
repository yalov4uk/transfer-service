package com.yalovchuk.transfer.service

import com.yalovchuk.transfer.component.TransferPublisher
import com.yalovchuk.transfer.dao.TransferDao
import com.yalovchuk.transfer.model.Status
import com.yalovchuk.transfer.model.Transfer
import spock.lang.Specification

class TransferServiceImplTest extends Specification {

    TransferDao transferDao = Mock()
    TransferPublisher transferProcessor = Mock()

    def transferService = new TransferServiceImpl(transferDao, transferProcessor)

    def "should create transfer and trigger it's processing"() {
        given:
        Transfer expected = new Transfer(1, 10, 1, 2, Status.CREATED)
        Transfer input = new Transfer(null, 10, 1, 2, null)
        transferDao.create(new Transfer(null, 10, 1, 2, Status.CREATED)) >> expected

        when:
        Transfer actual = transferService.create(input)

        then:
        actual == expected
        1 * transferProcessor.publish(expected)
    }

    def "should return transfer"() {
        given:
        Transfer expected = new Transfer(1, 10, 1, 2, Status.APPROVED)
        transferDao.get(1) >> expected

        when:
        Transfer actual = transferService.get(1)

        then:
        actual == expected
    }
}
