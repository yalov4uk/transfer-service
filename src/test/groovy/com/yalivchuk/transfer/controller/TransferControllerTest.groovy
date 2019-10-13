package com.yalivchuk.transfer.controller

import com.google.gson.Gson
import com.yalovchuk.transfer.Application
import com.yalovchuk.transfer.config.TransferRoute
import com.yalovchuk.transfer.dto.Transfer
import spark.Spark
import spock.lang.Shared
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class TransferControllerTest extends Specification {

    private static final String API = "http://localhost:4567"

    @Shared
    Gson gson = new Gson()

    def httpClient = HttpClient.newHttpClient()

    def setupSpec() {
        Application.main()
    }

    def cleanupSpec() {
        Spark.stop()
    }

    def "should create new transfer and return it's id"() {
        given:
        Transfer input = new Transfer(null, 10, 1, 2)
        String body = gson.toJson(input)
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API + TransferRoute.getTRANSFERS()))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()

        when:
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())

        then:
        httpResponse.statusCode() == 202
        Transfer actual = gson.fromJson(httpResponse.body(), Transfer.class)
        actual.getId() != null
        actual.getValue() == input.getValue()
        actual.getFromAccountId() == input.getFromAccountId()
        actual.getToAccountId() == input.getToAccountId()
    }

    def "should return transfer by id"() {
        given:
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API + TransferRoute.getTRANSFERS() + '/1'))
                .GET()
                .build()

        when:
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())

        then:
        Transfer expected = new Transfer(1, 10, 1, 2)
        httpResponse.statusCode() == 200
        Transfer actual = gson.fromJson(httpResponse.body(), Transfer.class)
        actual == expected
    }
}
