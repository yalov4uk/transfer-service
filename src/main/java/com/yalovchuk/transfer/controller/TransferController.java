package com.yalovchuk.transfer.controller;

import com.google.gson.Gson;
import com.yalovchuk.transfer.dto.ErrorDto;
import com.yalovchuk.transfer.model.Transfer;
import com.yalovchuk.transfer.service.TransferService;
import com.yalovchuk.transfer.validation.TransferValidator;
import lombok.RequiredArgsConstructor;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class TransferController {

    private final Gson gson;
    private final TransferValidator transferValidator;
    private final TransferService transferService;

    public String create(Request request, Response response) {
        Transfer input = gson.fromJson(request.body(), Transfer.class);

        if (!transferValidator.isValid(input)) {
            response.status(400);
            return gson.toJson(new ErrorDto());
        }

        Transfer output = transferService.create(input);
        response.status(202);
        return gson.toJson(output);
    }

    public String get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Transfer output = transferService.get(id);
        return gson.toJson(output);
    }
}
