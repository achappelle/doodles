package com.me.example.handlers;

import com.me.example.model.DaoMaker;
import com.me.example.model.MessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PostMessageRouteHandler extends RouteHandler<MessagePayload> {
    public final static String MessageIdParamKey = ":messageid";
    private final Logger log = LoggerFactory.getLogger(GetMessageRouteHandler.class);

    public PostMessageRouteHandler(DaoMaker daoMaker) {
        super(MessagePayload.class, daoMaker);
    }

    @Override
    protected Object processRoute(MessagePayload value, Map<String, String> pathParams, Set<String> urlParams) {
        log.info("Storing json=[" + toJson(value) + "] for key=" + pathParams.get(MessageIdParamKey));
        daoMaker.makeMessageDao().create(pathParams.get(MessageIdParamKey), toJson(value));
        return "";
    }
}
