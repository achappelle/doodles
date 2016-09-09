package com.me.example.filters;

import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class AuthenticationFilter implements Filter {
    @Override
    public void handle(Request request, Response response) throws Exception {
        // check auth from request state
        boolean authenticated = true;

        if (!authenticated) {
            halt(401, "Failed to authenticate!");
        }
    }
}
