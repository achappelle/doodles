package com.me.example.persistence

import java.util.concurrent.ConcurrentHashMap
import com.twitter.util.Future
import com.twitter.inject.Logging

object InMemoryMessageDao {
  val model: ConcurrentHashMap[String, String] = new ConcurrentHashMap[String, String]
}

class InMemoryMessageDao
  extends MessageDao
  with Logging {
  import InMemoryMessageDao._

  def get(key: String): Future[Option[String]] = Future {
    info("Using In Memory Dao for GET!!!")
    Option(model.get(key))
  }

  def set(key: String, value: String): Future[Unit] = Future {
    info("Using In Memory Dao for SET!!!")
    model.put(key, value)
  }

  def remove(key: String): Future[Unit] = Future {
    model.remove(key)
  }

  def update(key: String, value: String): Future[Unit] = Future {
    model.put(key, value)
  }
}
