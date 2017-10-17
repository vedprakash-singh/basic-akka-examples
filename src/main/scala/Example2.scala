import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

class Example1 extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "morning" => log.info("Good morning!")
    case "night" => log.info("Good night")
    case _ => log.info("Nothing...")
  }
}

object Example2 {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("day-system")
    val props = Props[Example1]
    val drinkActor = system.actorOf(props, "dayActor-1")
    drinkActor ! "morning"
    drinkActor ! "night"
    drinkActor ! "veryGoodMorning"
    system.terminate()
  }

}
