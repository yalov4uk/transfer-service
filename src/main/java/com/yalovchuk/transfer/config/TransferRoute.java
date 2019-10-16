package com.yalovchuk.transfer.config;

import com.yalovchuk.transfer.controller.TransferController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spark.Spark;
import spark.route.HttpMethod;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class TransferRoute {

    @Getter
    private static final String TRANSFERS = "/transfers";

    private final TransferController transferController;

    public void configure() {
        Spark.post(TRANSFERS, transferController::create);
        log.info("Exposed endpoint {} {}", HttpMethod.post.toString().toUpperCase(), TRANSFERS);

        Spark.get(TRANSFERS + "/:id", transferController::get);
        log.info("Exposed endpoint {} {}", HttpMethod.get.toString().toUpperCase(), TRANSFERS + "/:id");
    }
}
