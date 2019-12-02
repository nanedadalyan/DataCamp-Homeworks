import java.util.Date
import scala.io.StdIn._

class Workout(date: Date) {
  private var exercises = List[Exercise]()

  //**********  Getters  ***********
  def getDate():Date = {return this.date}

  //**********  Methods  ***********
  def addExercise(): Unit = {
    print("Enter name of exercise: ")
    val exerciseName = readLine()
    print("Enter type of exercise (strength, endurance, or cardio): ")
    val exerciseType = readLine()
    val exercise = new Exercise(exerciseName, exerciseType)
    print("If applicable, enter weights used (kg), otherwise '0': ")
    exercise.setWeightsUsed(readInt())
    print("Enter number of reps: ")
    exercise.setReps(readInt())
    print("Enter number of sets: ")
    exercise.setSets(readInt())
    print("If applicable, enter distance traveled during exercise (meters), otherwise '0': ")
    exercise.setDistance(readInt())
    this.exercises = this.exercises :+ exercise
  }

  def showExercises(): Unit = {
    for(exercise <- exercises) {
      println("Exercise Name: " + exercise.getName())
      println("Category: " + exercise.getType())
      println("Weights Used: "+exercise.getWeightsUsed())
      println("Reps: " + exercise.getReps())
      println("Sets: " + exercise.getSets())
      println("Distance: " + exercise.getDistance())
      println()
    }
  }

  def totalReps():Int = {
    var sum = 0
    exercises.foreach(exercise => sum += (exercise.getReps()) * exercise.getSets())
    print("Total Reps: " + sum)
    return sum
  }

  def totalWeightDisplaced():Int = {
    var sum = 0
    exercises.foreach(exercise => sum += (exercise.getWeightsUsed() * exercise.getReps()))
    print("\t\tTotal Weight Displaced: " + sum)
    return sum
  }

  def totalDistance():Int = {
    var sum = 0
    exercises.foreach(exercise => sum += exercise.getDistance())
    print("\t\tTotal Distance: " + sum)
    println("\n")
    return sum
  }
}
