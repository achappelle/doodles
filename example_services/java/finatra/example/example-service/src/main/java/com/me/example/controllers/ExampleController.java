package com.me.example.controllers;

import com.me.example.handlers.GetMessageHandler;
import com.me.example.handlers.PostMessageHandler;
import com.me.example.persistence.MessageDao;
import com.twitter.finatra.http.AbstractController;
import com.twitter.inject.Logging;

import javax.inject.Inject;
import java.util.Optional;

public class ExampleController extends AbstractController {

    @Inject
    MessageDao messageDao;

    @Override
    public void configureRoutes() {

        get("/message/:id", request ->
            Optional.ofNullable(new GetMessageHandler(messageDao).handle(request)).orElseGet(() ->
                    response().notFound("Failed to find message for id=" + request.getParam("id"))
            )
        );

        post("/message/:id", request -> new PostMessageHandler(messageDao).handle(request));
    }
}