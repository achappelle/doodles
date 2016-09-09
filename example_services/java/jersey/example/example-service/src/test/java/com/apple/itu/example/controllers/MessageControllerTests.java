package com.me.itu.example.controllers;

import com.me.itu.example.persistence.Message;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class MessageControllerTests extends JerseyTest {
    final String RootPath = "example/rest/v1/message/";

    @Override
    protected Application configure() {
        return new ResourceConfig(MessageController.class);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        final String messageId = "notFoundMessageID";
        final Message message = target(RootPath + messageId).request().get(Message.class);
    }

    @Test
    public void testPostThenGet() {
        final String messageId = "messageID";
        Message message = new Message(messageId);
        target(RootPath + messageId).request().post(Entity.entity(message,MediaType.APPLICATION_JSON_TYPE), Message.class);

        final Message responseMessage = target(RootPath + messageId).request(MediaType.APPLICATION_JSON_TYPE).get(Message.class);
        assertEquals(message.getContent(), responseMessage.getContent());
    }
}
