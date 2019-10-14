package com.yalovchuk.transfer.component;

import com.yalovchuk.transfer.model.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.BlockingQueue;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class AsyncTransferProcessorImpl implements TransferProcessor {

    private final BlockingQueue<Transfer> queue;

    @Override
    @SneakyThrows
    public void process(Transfer transfer) {
        queue.put(transfer);
    }
}
