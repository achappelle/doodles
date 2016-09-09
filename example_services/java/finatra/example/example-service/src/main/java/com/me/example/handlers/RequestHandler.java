package com.me.example.handlers;

import com.twitter.finagle.http.Request;
import com.twitter.finatra.http.exceptions.HttpException;

public interface RequestHandler {
    Object handle(Request request);
}

