package com.me.itu.example.handlers;

import com.me.itu.example.persistence.DaoMaker;
import com.me.itu.example.persistence.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

public class GetMessageHandler implements RequestHandler {
    private String messageId;
    private DaoMaker daoMaker;
    private static Logger logger = LoggerFactory.getLogger(GetMessageHandler.class.getName());

    public GetMessageHandler(String messageId, DaoMaker daoMaker ) {
        this.messageId = messageId;
        this.daoMaker = daoMaker;
    }

    public Object handle() {
        logger.info("Handling message for messageId=" + messageId);
        return Optional.ofNullable(daoMaker.makeMessageDao().get(messageId)).map(Message::new).orElseGet(() -> {
            String errorMessage = "Message not found for messageId=" + messageId;
            logger.warn(errorMessage);
            throw new NotFoundException(errorMessage);
        });
    }
}
