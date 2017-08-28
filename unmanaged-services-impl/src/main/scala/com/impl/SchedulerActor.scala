package com.impl
import com.api.ExternalService
import akka.actor.{Actor, Props}
import play.api.Logger
class SchedulerActor (externalService: ExternalService) extends Actor {

  override def receive: Receive = {

    case "scheduler" =>
      val userData = externalService.getUser().invoke()
      userData.map(userInfo => Logger.info("User Id : " + userInfo.id + "User Title : "
        + userInfo.title + "User Body : " + userInfo.body))

    case _ =>
      Logger.info("Invalid parameters")
  }
}
object SchedulerActor {

  def props (externalService: ExternalService): Props =
    Props(classOf[SchedulerActor], externalService)
}
