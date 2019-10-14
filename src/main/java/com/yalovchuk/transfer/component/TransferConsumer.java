package com.yalovchuk.transfer.component;

import com.yalovchuk.transfer.model.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
@RequiredArgsConstructor
public class TransferConsumer implements Runnable {

    @Setter
    private volatile boolean active = true;

    private final BlockingQueue<Transfer> queue;
    private final TransferHandler transferHandler;

    @Override
    @SneakyThrows
    public void run() {
        while (active) {
            Transfer transfer = queue.take();
            log.debug("Thread {} receive transfer with id = {}", Thread.currentThread().getName(), transfer.getId());
            transferHandler.handle(transfer);
        }
    }
}
