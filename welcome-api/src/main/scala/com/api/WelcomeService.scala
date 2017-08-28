package com.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

trait WelcomeService extends Service {

  def welcomeUser (id: String): ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._
    // @formatter:off
    named("welcome")
      .withCalls(
        pathCall("/welcome/api/welcome-user/:id", welcomeUser _)
      ).withAutoAcl(true)
  }

}
