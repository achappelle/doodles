package com.me.example.service;

import com.me.example.controllers.ExampleController;
import com.me.example.modules.MessageDaoModule;
import com.twitter.finatra.http.AbstractHttpServer;
import com.twitter.finatra.http.filters.CommonFilters;
import com.twitter.finatra.http.filters.LoggingMDCFilter;
import com.twitter.finatra.http.filters.TraceIdMDCFilter;
import com.twitter.finatra.http.routing.HttpRouter;

import javax.inject.Singleton;

@Singleton
class ExampleServer extends AbstractHttpServer {

    public ExampleServer() {
        addFrameworkModule(new MessageDaoModule());
    }

    @Override
    public void configureHttp(HttpRouter router) {
        router
            .filter(new LoggingMDCFilter<>())
            .filter(new TraceIdMDCFilter<>())
            .filter(CommonFilters.class)
            .add(ExampleController.class);
    }
}