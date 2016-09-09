package com.me.example.handlers

import javax.inject.Inject

import com.me.example.domain.http.PostMessageRequest
import com.me.example.persistence.MessageDao
import com.twitter.inject.Logging
import com.twitter.util.Future

class PostMessageHandler @Inject()(
  dao: MessageDao,
  request: PostMessageRequest
) extends RequestHandler
  with Logging {

  override def handle(): Future[Any] = {
    info(s"Processing: $request")
    dao.set(request.id, request.message).map { _ => () }
  }
}