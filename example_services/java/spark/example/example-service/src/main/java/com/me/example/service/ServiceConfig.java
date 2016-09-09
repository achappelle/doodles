package com.me.example.service;

import java.util.Arrays;
import java.util.List;

public class ServiceConfig {
    // todo: load from properties
    public String rootPath;
    public List<String> oldRootPaths;
    public int port;
    public int maxThreads = 8;
    public int minThreads = 2;
    public int timeoutMillis = 30000;

    final static String OldRootPath = "example/rest";
    final static String RootPath = "/example/rest/v1";

    public ServiceConfig setRootPath(String rootPath) {
        this.rootPath = rootPath;
        return this;
    }

    public ServiceConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public ServiceConfig setOldRootPaths(List<String> oldRootPaths) {
        this.oldRootPaths = oldRootPaths;
        return this;
    }

    public static ServiceConfig createFromProperties() {
        return new ServiceConfig()
                .setRootPath(RootPath)
                .setOldRootPaths(Arrays.asList(OldRootPath))
                .setPort(9090);
    }
}
