package com.me.example.handlers;

import com.me.example.model.DaoMaker;
import com.me.example.model.EmptyPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static spark.Spark.halt;

public class GetMessageRouteHandler extends RouteHandler<EmptyPayload> {
    public final static String MessageIdParamKey = ":messageid";
    private final Logger log = LoggerFactory.getLogger(GetMessageRouteHandler.class);

    public GetMessageRouteHandler(DaoMaker daoMaker) {
        super(EmptyPayload.class, daoMaker);
    }

    @Override
    protected Object processRoute(
            EmptyPayload payload,
            Map<String, String> pathParams,
            Set<String> urlParams
    ) throws Exception {
        String key = pathParams.get(MessageIdParamKey);
        Optional<String> value = daoMaker.makeMessageDao().get(key);
        if (value.isPresent()) {
            return value.get();
        } else {
            // halt will stop the request here and
            // return a the specified error code
            halt(404, "no value for key=" + key);
        }

        // if you don't like halt, you could also just throw here
        // and handle it in the your exception mapper
        return null;
    }
}
