import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date

import scala.io.Source
import scala.io.StdIn.{readInt, readLine}

class User() {
  private var workouts = List[Workout]()
  private var name = ""; private var age = 0; private var heightCm = 0.0
  private var weightK = 0.0

  def userProfile(): String = {
    try {
      val fileSource = Source.fromFile("user_details.txt")
      val lines = fileSource.getLines()
      name = lines.next(); age = lines.next().toInt; heightCm = lines.next().toDouble
      weightK = lines.next().toDouble
      fileSource.close
      return name
    } catch{
      case a: java.io.FileNotFoundException =>
        val writeUser = new PrintWriter("user_details.txt")
        name = readLine("Type your name to add a user: ")
        writeUser.println(name)
        print("Age: "); age = readInt()
        writeUser.println(age)
        print("Height (cm): "); heightCm = readInt()
        writeUser.println(heightCm)
        print("Weight (kg): "); weightK = readInt()
        writeUser.println(weightK)
        writeUser.close()
        return name
    }
  }

  def updateMenu(): Unit = {
    var answer = 0
    var updatedData = 0; var newName = ""
    do {
      println("Name: " + this.name + "\t\tAge: " + this.age + "\t\tHeight: " + this.heightCm +
        "\t\tWeight: " + this.weightK)
      println("1: Update name\n" + "2: Update age\n" + "3: Update height\n" + "4: Update weight\n" +
        "5: Go back to previous menu")
      answer = readInt()
      answer match {
        case 1 => newName= readLine("Enter new name: ")
                  updateName(newName)
        case 2 => print("Enter new age: ")
                  updatedData = readInt()
                  updateAge(updatedData)
        case 3 => print("Enter new height: ")
                  updatedData = readInt()
                  updateHeight(updatedData)
        case 4 => print("Enter new weight: ")
                  updatedData = readInt()
                  updateWeight(updatedData)
        case 5 => answer = 5
        case _ => answer = 0
      }
    } while (answer != 5)
    val writeUser = new PrintWriter("user_details.txt")
    writeUser.println(name); writeUser.println(age); writeUser.println(heightCm)
    writeUser.println(weightK)
    writeUser.close()
  }

  def updateName(name: String): Unit = {
    this.name = name
  }

  def updateAge(age: Int): Unit = {
    this.age = age
  }

  def updateHeight(heightCm: Int): Unit = {
    this.heightCm = heightCm
  }

  def updateWeight(weightK: Int): Unit = {
    this.weightK = weightK
  }

  def addWorkout(date: Date): Workout = {
    var answer = 1
    val workout = new Workout(date)
    do {
      println("\nPress '1' to add an exercise or '0' to go back to previous menu:")
      answer = readInt()
      if (answer !=0 ) workout.addExercise()
    } while(answer != 0)
    workouts = workouts :+ workout
    return workout
  }

  def showWorkouts(): Unit = {
    var n = 0; var answer = 0
    for (workout <- workouts) {
      n +=1
      println(n + ": " + workout.getDate())
    }
    do {
      if (workouts.size < 1) {
        println("Sorry, there are no saved workouts\n")
        return
      } else {
        println("\nEnter the number of the workout to view its details\n" +
          "or '0' to go back to the previous menu: ")
      }
      answer = readInt() - 1
      if (answer < 0 || answer >= workouts.size) throw new ArrayIndexOutOfBoundsException
      else {
        seeWorkoutDetails(workouts(answer))
      }
    } while (answer != 0)
  }

  def seeWorkoutDetails(workout: Workout): Unit = {
    workout.showExercises()
    workout.totalReps()
    workout.totalWeightDisplaced()
    workout.totalDistance()
  }
}
