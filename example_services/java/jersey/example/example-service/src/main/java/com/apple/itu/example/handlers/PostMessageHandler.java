package com.me.itu.example.handlers;

import com.me.itu.example.persistence.DaoMaker;
import com.me.itu.example.persistence.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostMessageHandler implements RequestHandler {
    private String messageId;
    private Message message;
    private DaoMaker daoMaker;
    private static Logger logger = LoggerFactory.getLogger(PostMessageHandler.class.getName());

    public PostMessageHandler(String messageId, Message message, DaoMaker daoMaker) {
        this.messageId = messageId;
        this.message = message;
        this.daoMaker = daoMaker;
    }

    public Object handle() {
        daoMaker.makeMessageDao().create(messageId, message.getContent());
        logger.info("Created message for messageId=" + messageId + ", message=" + message.getContent());

        // probably a better way to do this
        return null;
    }
}
