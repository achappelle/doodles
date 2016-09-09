package com.me.itu.example.controllers;

import org.glassfish.jersey.server.ManagedAsync;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "ping" path)
 */
@Path("ping")
@Produces(MediaType.TEXT_PLAIN)
public class PingController {

    @GET
    @ManagedAsync
    public void ping(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume("OK");
    }
}
