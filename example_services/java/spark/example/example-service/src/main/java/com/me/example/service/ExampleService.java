package com.me.example.service;

public class ExampleService {
    public static void main(String[] args)  {
        ServiceInitializer.init(ServiceConfig.createFromProperties());
        //DebugScreen.enableDebugScreen();
    }
}

/*
public class Main {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "hi!");
    }
}
*/