package com.me.example.domain.http

import com.me.example.Request
import com.twitter.finatra.request.RouteParam

case class GetMessageRequest(@RouteParam id: String) extends Request
