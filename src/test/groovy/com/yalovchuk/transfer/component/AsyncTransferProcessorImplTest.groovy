package com.yalovchuk.transfer.component

import com.yalovchuk.transfer.model.Status
import com.yalovchuk.transfer.model.Transfer
import spock.lang.Specification

import java.util.concurrent.BlockingQueue

class AsyncTransferProcessorImplTest extends Specification {

    BlockingQueue queue = Mock()

    def transferProcessor = new AsyncTransferProcessorImpl(queue)

    def "should add transfer to a queue"() {
        given:
        Transfer input = new Transfer(1, 10, 1, 2, Status.CREATED)

        when:
        transferProcessor.process(input)

        then:
        1 * queue.put(input)
    }
}
