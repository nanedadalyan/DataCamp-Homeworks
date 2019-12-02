import java.util.Date
import scala.io.StdIn._

object FitnessTracker {
  def main(args: Array[String]): Unit = {
    val date = new Date()
    var answer = 0
    val currentUser = new User()
    val name = currentUser.userProfile()

    println("\nWelcome " + name + "!\nEnter a number to pick an option from the menu -->")
    do {
      println("1: Add exercises to today's workout\n" +
        "2: Show all workouts on record\n" +
        "3: Update personal details\n" +
        "4: Exit fitness tracker\n")
      answer = readInt()
      answer match {
        case 1 => currentUser.addWorkout(date)
        case 2 => currentUser.showWorkouts()
        case 3 => currentUser.updateMenu()
        case 4 => answer = 4
        case _ => answer = 0
      }
    } while (answer != 4)
  }
}
