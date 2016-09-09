package com.me.example.model;

public class MemoryDaoMaker implements DaoMaker {
    private static final MessageMemoryDao MessageDao = new MessageMemoryDao();

    public MessageDao makeMessageDao() { return MessageDao; }
}
