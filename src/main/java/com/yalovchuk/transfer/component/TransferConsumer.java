package com.yalovchuk.transfer.component;

import com.google.gson.Gson;
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

    private final BlockingQueue<String> queue;
    private final TransferHandler transferHandler;
    private final Gson gson;

    @Override
    @SneakyThrows
    public void run() {
        while (active) {
            String payload = queue.take();
            Transfer transfer = gson.fromJson(payload, Transfer.class);
            log.debug("Thread {} receive transfer with id = {}", Thread.currentThread().getName(), transfer.getId());
            transferHandler.handle(transfer);
        }
    }
}
