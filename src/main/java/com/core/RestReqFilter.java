package com.core;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class RestReqFilter implements Filter {


    private static List<Integer> desiredStatusCode = Arrays.asList(200, 201);
    private final Logger log = LoggerFactory.getLogger(RestReqFilter.class);

    public RestReqFilter(List<Integer> statusCode) {
        desiredStatusCode = statusCode;
    }

    public RestReqFilter() {
        desiredStatusCode = List.of(200, 201);
    }

    @Override
    public Response filter(FilterableRequestSpecification reqSpec, FilterableResponseSpecification resSpec, FilterContext filterContext) {
        var response = filterContext.next(reqSpec, resSpec);
        if (!desiredStatusCode.contains(0) && !desiredStatusCode.contains(response.statusCode())) {
            log.error("\nResponse code: {} \nResponse Body: {} \nYou can use following cURL to reproduce the same request {}",
                    response.statusCode(),
                    response.asPrettyString(),
                    createCurl(reqSpec.getMethod(), reqSpec.getURI(), reqSpec.getHeaders(), reqSpec.getBody()));
        }
        return response;
    }


    private String createCurl(String method, String url, Headers headers, String body) {
        StringBuilder curl = new StringBuilder(String.format("%n curl --location --request %s %s \\\n", method, url));

        if (headers.size() > 1) {
            headers.asList().forEach(h -> curl.append(String.format("--header '%s'\\\n", h.toString().replaceFirst("=", ":"))));
        }
        if (body != null && !body.isEmpty() && !body.isBlank() && !body.equals("null")) {
            curl.append(String.format("--data-raw '%s'", body));
        }
        return curl.toString();
    }

}
