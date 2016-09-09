package com.me.example.handlers;

import com.me.example.persistence.MessageDao;
import com.twitter.finagle.http.Request;


public class PostMessageHandler implements RequestHandler {

    private final MessageDao messageDao;

    public PostMessageHandler(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Object handle(Request request) {
        String key = request.getParam("id");
        messageDao.set(key, request.contentString());
        return request.contentString();
    }
}
