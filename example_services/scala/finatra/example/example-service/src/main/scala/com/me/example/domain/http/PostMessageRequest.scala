package com.me.example.domain.http

import com.twitter.finatra.request.RouteParam

case class PostMessageRequest(@RouteParam id: String, message: String)
