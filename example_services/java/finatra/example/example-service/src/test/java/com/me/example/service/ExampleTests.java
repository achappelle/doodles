package com.me.example.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.net.MediaType;
import com.google.inject.Stage;
import com.twitter.finagle.http.Request;
import com.twitter.finagle.http.Response;
import com.twitter.finagle.http.Status;
import com.twitter.finatra.http.EmbeddedHttpServer;
import org.junit.Test;

import static com.twitter.finatra.httpclient.RequestBuilder.get;
import static com.twitter.finatra.httpclient.RequestBuilder.post;
import static org.junit.Assert.assertEquals;

public class ExampleTests {

    private EmbeddedHttpServer server = new EmbeddedHttpServer(
            new ExampleServer(),
            ImmutableMap.of(),
            Stage.DEVELOPMENT
    );

    @Test
    public void testGetMessageEndpoint() {
        Request request = get("/message/bob");
        Response response = server.httpRequest(request);
        assertEquals(Status.NotFound(), response.status());
    }

    @Test
    public void testPostMessageWithIDEndpoint() {
        String id = "pickle";
        String message = "sour";
        String json = "{\"content\":\"" + message + "\"}";

        Request request = post("/message/" + id).body(json, MediaType.JSON_UTF_8.toString());
        Response response = server.httpRequest(request);
        assertEquals(Status.Ok(), response.status());
        assertEquals(json, response.contentString());

        Request getRequest = get("/message/" + id);
        Response getResponse = server.httpRequest(getRequest);
        assertEquals(Status.Ok(), getResponse.status());
        assertEquals(json, getResponse.contentString());
    }
}
