package com.me.itu.example.controllers;

import com.me.itu.example.handlers.RequestHandler;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;

public class Controller {

    protected void handleRequest(AsyncResponse asyncResponse,  RequestHandler messageHandler) {
        try {
            Object object = messageHandler.handle();
            asyncResponse.resume(object);
        } catch(WebApplicationException ex) {
            asyncResponse.resume(ex);
        }
    }
}
