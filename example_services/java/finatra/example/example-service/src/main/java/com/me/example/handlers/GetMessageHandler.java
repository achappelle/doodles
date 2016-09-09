package com.me.example.handlers;

import com.me.example.persistence.MessageDao;
import com.twitter.finagle.http.Request;
import com.twitter.finagle.http.Response;
import com.twitter.finagle.http.Status;
import com.twitter.finatra.http.exceptions.HttpException;
import com.twitter.finatra.http.exceptions.NotFoundException;
import com.twitter.finatra.http.response.ResponseBuilder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

public class GetMessageHandler implements RequestHandler {
    private final MessageDao messageDao;
    public GetMessageHandler(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Object handle(Request request) {
        return messageDao.get(request.getParam("id")).orElse(null);
    }
}
