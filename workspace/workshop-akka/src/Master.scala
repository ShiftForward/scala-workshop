import akka.actor._
import com.typesafe.config.ConfigFactory

class ChatActor extends Actor {
  def receive = {
    case Join(s) ⇒ println("--> | " + s + " has joined.")  
    case Message(s, m) ⇒ println(s + ": " + m)
    case Quit(s) ⇒ println("<-- | " + s + " has left.")
  }
}

object Master extends App {
  val system = ActorSystem("WorkshopSystem", ConfigFactory.load.getConfig("master"))
  system.actorOf(Props[ChatActor], "master")
}