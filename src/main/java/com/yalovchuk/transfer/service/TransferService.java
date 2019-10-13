package com.yalovchuk.transfer.service;

import com.yalovchuk.transfer.model.Transfer;

public interface TransferService {

    Transfer create(Transfer transfer);

    Transfer get(int id);
}
