package com.yalovchuk.transfer.config;

import com.yalovchuk.transfer.controller.TransferController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import spark.Spark;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class TransferRoute {

    @Getter
    private static final String TRANSFERS = "/transfers";

    private final TransferController transferController;

    public void configure() {
        Spark.post(TRANSFERS, transferController::create);
        Spark.get(TRANSFERS + "/:id", transferController::get);
    }
}
