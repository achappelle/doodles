package com.me.example.handlers

import javax.inject.Inject

import com.me.example.domain.http.GetMessageRequest
import com.me.example.persistence.MessageDao
import com.twitter.util.Future

class GetMessageHandler @Inject()(
  dao: MessageDao,
  request: GetMessageRequest
) extends RequestHandler {

  override def handle(): Future[Any] = {
    dao.get(request.id)
  }
}