package com.impl
import akka.actor.ActorSystem
import com.api.{ExternalService, UserData, UserService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class UserServiceImpl (externalService: ExternalService)
                      (implicit ec: ExecutionContext) extends UserService {

  override def greetUser (name: String) = ServiceCall {
    _ => Future.successful("Hello " + name)
  }

  override def testUser () = ServiceCall { _ =>
    val result: Future[UserData] = externalService.getUser().invoke()
    result.map(response => response.toString)
  }

  val actorSystem =ActorSystem("UserSystemActor")

  val lagomProducerActor = actorSystem.actorOf(SchedulerActor.props(externalService))

  actorSystem.scheduler.schedule(
    10 seconds,
    10 seconds,
    lagomProducerActor,
    "scheduler"
  )
}
