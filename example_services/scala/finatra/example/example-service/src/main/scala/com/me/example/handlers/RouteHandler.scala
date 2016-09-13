package com.me.example.handlers

import com.twitter.util.Future

trait RouteHandler {
  def handle(): Future[Any]
}

