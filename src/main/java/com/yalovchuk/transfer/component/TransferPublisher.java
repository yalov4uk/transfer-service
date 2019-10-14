package com.yalovchuk.transfer.component;

import com.yalovchuk.transfer.model.Transfer;

public interface TransferPublisher {

    void publish(Transfer transfer);
}
