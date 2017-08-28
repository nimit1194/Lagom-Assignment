package com.api
import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
trait UserService extends Service {

  def greetUser (name:String): ServiceCall[NotUsed, String]
  def testUser (): ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._
    named("user")
      .withCalls(
        restCall(Method.GET,"/api/user/:id", greetUser _),
        restCall(Method.GET,"/api/userdata", testUser _)
      ).withAutoAcl(true)

  }
}
