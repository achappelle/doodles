package com.me.example.service;

import com.me.example.exceptions.CustomException;
import com.me.example.filters.AuthenticationFilter;
import com.me.example.handlers.GetMessageRouteHandler;
import com.me.example.handlers.PostMessageRouteHandler;
import com.me.example.model.DaoMaker;
import com.me.example.model.MemoryDaoMaker;

import java.util.List;

import static spark.Spark.*;

class ServiceInitializer {
    private static void setupThreads(ServiceConfig config) {
        threadPool(config.maxThreads, config.minThreads, config.timeoutMillis);
    }

    private static void setupRedirects(List<String> oldRootPaths, String curRootPath) {
        // redirecting old API's to our new ones
        for(String oldRootPath : oldRootPaths){
            redirect.get(oldRootPath + "/message/:id", curRootPath + "/message/:id");
        }
    }

    // all the routes
    private static void setupRoutes(String rootPath, DaoMaker daoMaker) {
        get("/ping", (request, response) -> "OK");
        get(rootPath + "/message/" + GetMessageRouteHandler.MessageIdParamKey, new GetMessageRouteHandler(daoMaker));
        post(rootPath + "/message/" + PostMessageRouteHandler.MessageIdParamKey, new PostMessageRouteHandler(daoMaker));

        // "splatting" get("/message/*/something/*" ... puts splats in req.splat()[0]...
        // support for multipart uploads, e.g. file uploads
        // support for different template engines: Velocity, FreeMaker, Mustache, etc...
        // support for websockets
    }

    // Upstream request filters
    private static void setupBeforeFilters() {
        before(new AuthenticationFilter());
    }

    // Downstream response filters
    private static void setupAfterFilters(String rootPath) {
        // turns on gzip compression on if the request also allows it
        after(rootPath + "/message/:id", (request, response) -> response.header("Content-Encoding", "gzip"));
    }

    private static void setupExceptionHandlers() {
        // registering custom exception handling
        exception(CustomException.class, (exception, request, response) -> {
            // set the response status
            //response.status(some_error_code)
        });
    }

    static void init(ServiceConfig config) {
        setupThreads(config);
        port(config.port);
        //secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword); // setting up ssl
        setupBeforeFilters();
        setupRoutes(config.rootPath, new MemoryDaoMaker());
        setupAfterFilters(config.rootPath);
        setupRedirects(config.oldRootPaths, config.rootPath);
        setupExceptionHandlers();
    }
}
