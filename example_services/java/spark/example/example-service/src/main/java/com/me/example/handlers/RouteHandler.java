package com.me.example.handlers;

import com.me.example.model.DaoMaker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Set;

public abstract class RouteHandler<T> implements Route {
    private final Class<T> valueClass;
    protected final DaoMaker daoMaker;
    private final ObjectMapper mapper;
    private final Logger log = LoggerFactory.getLogger(GetMessageRouteHandler.class);

    public RouteHandler(Class<T> valueClass, DaoMaker daoMaker){
        this.valueClass = valueClass;
        this.daoMaker = daoMaker;
        this.mapper = new ObjectMapper();
    }

    public T fromJson(String json) {
        log.info("got here: 4, json=" + json);

        if (StringUtils.isEmpty(json)) {
            return null;
        }
        log.info("got here: 5");

        try {
            return mapper.readValue(json, valueClass);
        } catch (IOException e) {
            log.info("got here: 6");
            throw new RuntimeException("Failed to convert object to json:", e);
        }
    }

    public String toJson(Object data) {
        if (data == null) {
            return null;
        }

        try {
            return mapper.writeValueAsString(data);
        } catch (IOException e){
            throw new RuntimeException("Failed to convert object to json:", e);
        }
    }

    // entry point for testing
    public final Object process(T payload, Map<String,String> pathParams, Set<String> urlParams) throws Exception {
        return processRoute(payload, pathParams, urlParams);
    }

    private Object process(Request request) throws Exception {
        for(Map.Entry<String,String> entry : request.params().entrySet()) {
            log.info(entry.getKey() + "->" + entry.getValue());
        }
        return process(
            fromJson(request.body()),
            request.params(),
            request.queryParams()
        );
    }

    protected abstract Object processRoute(T value, Map<String, String> pathParams, Set<String> urlParams) throws Exception;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        return process(request);
    }
}
