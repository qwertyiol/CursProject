package com.netcracker.curs.fapi.interseptors;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;


public class AuthInterceptor implements ClientHttpRequestInterceptor {
    private final String headerValue;

    public AuthInterceptor(String headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().add("Authorization", headerValue);
        return execution.execute(requestWrapper, body);
    }
}