package com.me.itu.example.persistence;

public class MemoryDaoMaker implements DaoMaker {
    private static final MessageMemoryDao MessageDao = new MessageMemoryDao();

    public MessageDao makeMessageDao() { return MessageDao; }
}
