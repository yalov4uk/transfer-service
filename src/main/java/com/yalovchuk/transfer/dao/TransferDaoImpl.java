package com.yalovchuk.transfer.dao;

import com.yalovchuk.transfer.model.Status;
import com.yalovchuk.transfer.model.Transfer;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class TransferDaoImpl implements TransferDao {

    private final Map<Integer, Transfer> transfers = List.of(
            new Transfer(1, 10, 1, 2, Status.CREATED))
            .stream()
            .collect(Collectors.toMap(Transfer::getId, Function.identity()));
    private AtomicInteger counter = new AtomicInteger(1);

    @Override
    public Transfer create(Transfer transfer) {
        int id = counter.incrementAndGet();
        transfer.setId(id);
        transfers.put(id, transfer);
        return transfer;
    }

    @Override
    public Transfer update(Transfer transfer) {
        return null;
    }

    @Override
    public Transfer get(int id) {
        return transfers.get(id);
    }
}
