package com.yalovchuk.transfer.component

import com.google.gson.Gson
import com.yalovchuk.transfer.model.Status
import com.yalovchuk.transfer.model.Transfer
import com.yalovchuk.transfer.component.TransferPublisherImpl
import spock.lang.Specification

import java.util.concurrent.BlockingQueue

class TransferPublisherImplTest extends Specification {

    BlockingQueue queue = Mock()
    def gson = new Gson()

    def transferProcessor = new TransferPublisherImpl(queue, gson)

    def "should add transfer to a queue"() {
        given:
        Transfer input = new Transfer(1, 10, 1, 2, Status.CREATED)
        String expected =  gson.toJson(input)

        when:
        transferProcessor.publish(input)

        then:
        1 * queue.put(expected)
    }
}
