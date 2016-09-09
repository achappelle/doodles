package com.me.itu.example.controllers;

import com.me.itu.example.handlers.GetMessageHandler;
import com.me.itu.example.handlers.PostMessageHandler;
import com.me.itu.example.persistence.Message;
import com.me.itu.example.persistence.MemoryDaoMaker;
import org.glassfish.jersey.server.ManagedAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("example/rest/v1/message")
public class MessageController extends Controller {
    private static Logger logger = LoggerFactory.getLogger(GetMessageHandler.class.getName());

    // todo: use DI
    protected MemoryDaoMaker daoMaker = new MemoryDaoMaker();

    @POST
    @ManagedAsync
    @Path("{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateJobStatus(
            @Suspended final AsyncResponse asyncResponse,
            @PathParam("messageId") final String messageId,
            Message message
    ) {
        logger.info("Processing POST request for messageId=" + messageId);
        handleRequest(asyncResponse, new PostMessageHandler(messageId, message, daoMaker));
    }

    @GET
    @ManagedAsync
    @Path("{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getJobInfo(
            @Suspended final AsyncResponse asyncResponse,
            @PathParam("messageId") final String messageId
    ) {
        logger.info("Processing GET request for messageId=" + messageId);
        handleRequest(asyncResponse, new GetMessageHandler(messageId, daoMaker));
    }
}
