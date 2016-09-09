package com.me.example.modules

import javax.inject.Singleton

import com.me.example.persistence.{MessageDao, SomeRealMessageDao}
import com.google.inject.Provides
import com.twitter.inject.TwitterModule

object MessageDaoModule extends TwitterModule {

  @Singleton
  @Provides
  def providesMessageDao: MessageDao = {
    new SomeRealMessageDao
  }
}
