package com.me.example.handlers

import javax.inject.Inject

import com.me.example.domain.http.GetMessageRequest
import com.me.example.persistence.MessageDao
import com.twitter.util.Future

class GetMessageRouteHandler @Inject()(
  dao: MessageDao,
  request: GetMessageRequest
) extends RouteHandler {

  override def handle(): Future[Any] = {
    dao.get(request.id)
  }
}