package com.me.example.persistence

import java.util.concurrent.ConcurrentHashMap
import com.twitter.inject.Logging
import com.twitter.util.Future

object SomeRealMessageDao {
  val model: ConcurrentHashMap[String, String] = new ConcurrentHashMap[String, String]
}

class SomeRealMessageDao
  extends MessageDao
  with Logging {
  import InMemoryMessageDao._

  def get(key: String): Future[Option[String]] = Future {
    info("Using SomeRealMessageDao for GET")
    Option(model.get(key))
  }

  def set(key: String, value: String): Future[Unit] = Future {
    info("Using SomeRealMessageDao for SET")
    model.put(key, value)
  }

  def remove(key: String): Future[Unit] = Future {
    model.remove(key)
  }

  def update(key: String, value: String): Future[Unit] = Future {
    model.put(key, value)
  }
}
