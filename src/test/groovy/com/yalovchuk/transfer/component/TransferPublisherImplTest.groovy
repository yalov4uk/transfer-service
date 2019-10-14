package com.yalovchuk.transfer.component

import com.yalovchuk.transfer.model.Status
import com.yalovchuk.transfer.model.Transfer
import spock.lang.Specification

import java.util.concurrent.BlockingQueue

class TransferPublisherImplTest extends Specification {

    BlockingQueue queue = Mock()

    def transferProcessor = new TransferPublisherImpl(queue)

    def "should add transfer to a queue"() {
        given:
        Transfer input = new Transfer(1, 10, 1, 2, Status.CREATED)

        when:
        transferProcessor.publish(input)

        then:
        1 * queue.put(input)
    }
}
