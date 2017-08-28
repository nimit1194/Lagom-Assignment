package com.impl
import akka.NotUsed
import com.api.WelcomeService
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

class WelcomeServiceImpl extends WelcomeService
{
   def welcomeUser (id: String): ServiceCall[NotUsed,String] = ServiceCall {_ =>
    Future.successful(s"Welcome , $id" )
  }
}
