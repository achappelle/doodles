package com.me.example.controllers

import javax.inject.{Inject, Singleton}

import com.me.example.domain.http.{GetMessageRequest, PostMessageRequest}
import com.me.example.handlers._
import com.me.example.persistence.MessageDao
import com.netflix.config.scala.DynamicStringProperty
import com.twitter.finatra.http.Controller
import com.twitter.inject.Logging

object ExampleController {
  val someStringProperty = DynamicStringProperty("key.for.someString.property", "Default String Value")
}

@Singleton
class ExampleController @Inject()(dao: MessageDao)
  extends Controller
  with Logging {

  import ExampleController._

  get("/message/:id") { request: GetMessageRequest =>
    info(s"Some String Property=${someStringProperty.get}")
    new GetMessageRouteHandler(dao, request).handle().map {
        case Some(value) => value
        case None => response.notFound(s"No Value for key=${request.id}")
    }
  }

  post("/message/:id") { request: PostMessageRequest =>
    new PostMessageRouteHandler(dao, request).handle()
  }
}