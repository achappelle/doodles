package com.me.example.handlers

import com.twitter.util.Future

trait RequestHandler {
  def handle(): Future[Any]
}

