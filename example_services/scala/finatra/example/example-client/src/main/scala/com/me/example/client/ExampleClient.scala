package com.me.example.client

import com.twitter.finatra.httpclient.{RichHttpClient, HttpClient}

object ExampleClient {
  val client = RichHttpClient.newClientService("")
}
