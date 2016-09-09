package com.me.itu.example;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class Exceptions {
    @Provider
    public static class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

        @Override
        public Response toResponse(WebApplicationException exception) {
            Response r = exception.getResponse();
            return Response.status(r.getStatus()).entity("Code:" + r.getStatus() + ":"
                    + exception.getMessage() + "\n").build();
        }
    }
}
