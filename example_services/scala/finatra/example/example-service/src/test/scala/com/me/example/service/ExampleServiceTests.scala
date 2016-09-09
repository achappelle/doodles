package com.me.example.service

import javax.inject.Singleton

import com.me.example.persistence.{InMemoryMessageDao, MessageDao}
import com.google.inject.Provides
import com.google.inject.testing.fieldbinder.Bind
import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.httpclient.RequestBuilder._
import com.twitter.inject.{Mockito, TwitterBaseModule}
import com.twitter.inject.server.FeatureTest
import org.junit.Assert._
import org.scalatest.Matchers

class ExampleServiceTests
  extends FeatureTest
  with Mockito {

  // Use our in memory message dao for tests
  @Bind val messageDao: MessageDao = new InMemoryMessageDao

  // create our server on loopback
  override val server = new EmbeddedHttpServer(new ExampleServer)

  "Server" should {

    "fail to GET message" in {
      val request = get(s"/message/doesntExist")
      val response = server.httpRequest(request)
      assertEquals(NotFound, response.status)
    }

    "POST then GET a message" in {
      val id = "myId"
      val message = "bla"
      val body = s"""{"id":"$id", "message":"$message"}"""
      val path = s"/message/$id"

      // set our message data
      server.httpPost(
        path = path,
        postBody = body,
        andExpect = Ok,
        withBody = ""
      )

      // now get our message back
      server.httpGet(
        path = path,
        andExpect = Ok,
        withBody = message
      )
    }
  }
}
