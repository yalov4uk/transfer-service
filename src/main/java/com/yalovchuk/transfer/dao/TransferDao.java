package com.yalovchuk.transfer.dao;

import com.yalovchuk.transfer.model.Transfer;

public interface TransferDao {

    Transfer create(Transfer transfer);

    Transfer update(Transfer transfer);

    Transfer get(int id);
}
