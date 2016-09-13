package com.me.example.filters

import javax.inject.Singleton

import com.netflix.config.scala.DynamicBooleanProperty
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.inject.Logging
import com.twitter.util.Future

object ExampleAuthFilter {
  val authFilterEnabled = DynamicBooleanProperty("key.for.auth.enabled", true)
}

@Singleton
class ExampleAuthFilter [R <: Request]
  extends SimpleFilter[R, Response]
  with Logging {

  import ExampleAuthFilter._

  override def apply(request: R, service: Service[R, Response]): Future[Response] = {
    if (authFilterEnabled.get) {
      info(s"Checking auth for request: ${request}")
      // check auth stuff here...
    }

    service(request)
  }
}
