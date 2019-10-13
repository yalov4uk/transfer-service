package com.yalovchuk.transfer.controller;

import com.google.gson.Gson;
import com.yalovchuk.transfer.dto.Transfer;
import lombok.RequiredArgsConstructor;
import spark.Request;
import spark.Response;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class TransferController {

    private final Gson gson;

    public String create(Request request, Response response) {
        String body = gson.toJson(new Transfer(1, 10, 1, 2));
        response.status(202);
        return body;
    }

    public String get(Request request, Response response) {
        String body = gson.toJson(new Transfer(1, 10, 1, 2));
        return body;
    }
}
