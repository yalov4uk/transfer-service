package com.yalovchuk.transfer.component;

import com.google.gson.Gson;
import com.yalovchuk.transfer.model.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.BlockingQueue;

@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class TransferPublisherImpl implements TransferPublisher {

    private final BlockingQueue<String> queue;
    private final Gson gson;

    @Override
    @SneakyThrows
    public void publish(Transfer transfer) {
        log.debug("Sending transfer with id = {}", transfer.getId());
        queue.put(gson.toJson(transfer));
    }
}
