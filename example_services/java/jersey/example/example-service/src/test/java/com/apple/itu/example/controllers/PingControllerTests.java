package com.me.itu.example.controllers;

import com.me.itu.example.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class PingControllerTests {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void beforeAll() throws Exception {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void afterAll() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testPing() {
        String responseMsg = target.path("ping").request().get(String.class);
        assertEquals("OK", responseMsg);
    }
}
