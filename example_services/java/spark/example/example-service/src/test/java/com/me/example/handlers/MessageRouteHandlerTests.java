package com.me.example.handlers;

import com.me.example.model.DaoMaker;
import com.me.example.model.EmptyPayload;
import com.me.example.model.MemoryDaoMaker;
import com.me.example.model.MessagePayload;
import org.junit.After;
import org.junit.Test;
import spark.HaltException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageRouteHandlerTests {
    DaoMaker daoMaker = new MemoryDaoMaker();
    String messageId = "myMessageId";

    @After
    public void afterEach() {
        daoMaker.makeMessageDao().delete(messageId);
    }

    @Test
    public void failToGetMessageForId() throws Exception {
        GetMessageRouteHandler handler = new GetMessageRouteHandler(daoMaker);
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put(GetMessageRouteHandler.MessageIdParamKey, messageId);

        boolean failed = false;
        try {
            handler.process(new EmptyPayload(), pathParams, null);
        } catch (HaltException e) {
            failed = true;
            assertEquals("no value for key=" + messageId, e.body());
        }

        assertTrue(failed);
    }

    @Test
    public void postAndGetMessageForId() throws Exception {
        String messageContent = "some content for messageId=" + messageId;
        MessagePayload messagePayload = new MessagePayload(messageContent);

        // POST a message
        PostMessageRouteHandler postHandler = new PostMessageRouteHandler(daoMaker);
        Map<String, String> postPathParams = new HashMap<>();
        postPathParams.put(PostMessageRouteHandler.MessageIdParamKey, messageId);
        assertEquals(postHandler.process(messagePayload, postPathParams, null), "");

        // GET the message back
        GetMessageRouteHandler getHandler = new GetMessageRouteHandler(daoMaker);
        Map<String, String> getPathParams = new HashMap<>();
        getPathParams.put(GetMessageRouteHandler.MessageIdParamKey, messageId);
        String jsonMessage = (String) getHandler.process(new EmptyPayload(), getPathParams, null);
        assertEquals(getHandler.toJson(messagePayload), jsonMessage);
    }
}
