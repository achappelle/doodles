package com.me.example.modules;

import com.me.example.persistence.InMemoryMessageDao;
import com.me.example.persistence.MessageDao;
import com.google.inject.Provides;
import com.twitter.inject.TwitterModule;

import javax.inject.Singleton;


public class MessageDaoModule extends TwitterModule {
    @Singleton
    @Provides
    MessageDao providesMessageDao() {
        return new InMemoryMessageDao();
    }
}
