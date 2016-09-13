package com.me.example.service

import com.me.example.controllers.ExampleController
import com.me.example.filters.ExampleAuthFilter
import com.netflix.config.{ConfigurationBasedDeploymentContext, ConfigurationManager}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter
import com.me.example.modules.MessageDaoModule
import com.twitter.inject.Logging

object ExampleServerMain extends ExampleServer

class ExampleServer extends HttpServer with Logging {

  ConfigurationManager.loadCascadedPropertiesFromResources("example")

  info(s"DeploymentCtx=${ConfigurationManager.getDeploymentContext.getApplicationId}")
  info(s"DeploymentCtx=${ConfigurationManager.getDeploymentContext.getDeploymentEnvironment}")
  info(s"DeploymentCtx=${ConfigurationManager.getDeploymentContext.getDeploymentDatacenter}")
  info(s"ConfigurationManager Installed=${ConfigurationManager.isConfigurationInstalled}")

  // this is how you turn off the admin services
  //override val disableAdminHttpServer = true

  // add some Guice Modules
  override def modules = Seq(MessageDaoModule)

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .filter[ExampleAuthFilter[Request]]
      .add[ExampleController]
  }
}