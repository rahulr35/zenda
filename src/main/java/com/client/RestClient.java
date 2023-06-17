package com.client;

import com.core.HttpMethod;
import com.core.RestReqFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RestClient {

    Logger log = LoggerFactory.getLogger(RestClient.class);

    public Response restCall(HttpMethod httpMethod, RequestSpecification requestSpecification, String url) {
        requestSpecification.filter(new RestReqFilter());
        Response response;
        switch (httpMethod) {
            case GET: response = requestSpecification.get(url); break;
            case PUT: response = requestSpecification.put(url); break;
            case POST: response = requestSpecification.post(url); break;
            case DELETE: response = requestSpecification.delete(url); break;
            case PATCH: response = requestSpecification.patch(url); break;
            default: log.error("Unknown HTTP method"); return null;
        }
        return response;
    }










}
