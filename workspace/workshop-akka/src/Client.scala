import akka.actor._
import com.typesafe.config.ConfigFactory

case class Join(sender: String) extends Serializable
case class Message(sender: String, content: String) extends Serializable
case class Quit(sender: String) extends Serializable

object Client extends App {
  def repl: Unit = {
    print("> ")
    val m = readLine 
    
    if (m != ":quit") {
      actor ! Message(userName, m)
      repl
    } else {
      actor ! Quit(userName)
    }
  } 
  
  val masterHostname = "0.0.0.0"
  val userName = "user1"
    
  val system = ActorSystem("WorkshopSystem", ConfigFactory.load.getConfig("client"))
  val remotePath = "akka.tcp://WorkshopSystem@" + masterHostname + ":2552/user/master"
  val actor = system.actorSelection(remotePath)
  
  actor ! Join(userName)
  
  repl
  
  system.shutdown()
}