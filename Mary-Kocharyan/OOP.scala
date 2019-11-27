  
import java.util.Date
import scala.concurrent.duration._

object Main extends App {
  val types = new TaskType()

  var task1 = new Task
  task1.date = new Date()
  task1.duration = Duration(2,"days")
  task1.status = false
  task1.note = "will be done ASAP"
  task1.title = "new Task"
  task1.taskType = types.WORK

  var todoList = new ToDo()
  todoList.insertTask(task1)

  var foundedTasks: Array[Task] = todoList.filterByType(types.WORK)

  foundedTasks.foreach( it => {
    println("Title is: " + it.title)
  })
}
//********************************************

class ToDo {
  var todoList: Array[Task] = Array()

  def insertTask(task: Task): Unit = {
    todoList = todoList :+ task
  }

   def filterByType(taskType: String): Array[Task] = {
    todoList.filter(_.taskType == taskType)
  }

  def getTasksByDate(date: Date): Array[Task] = {
    todoList.filter(_.date == date)
  }
  def priorityon(task:Task): Unit ={
    if (task.priority==false){
      println("Task is added to the priority list")
      task.priority = true
    }
    else println("task is already in the priority list")
  }
  def statusupdate(task:Task):Unit = {
    if (task.status == false){
      task.status = true
    }
    else {
      task.status = false
    }
  }
  def durationupdate (task:Task, newduration:Duration):Unit ={
    task.duration = newduration
  }
  
}

trait ToDoApplication {
  def insertTask(task: Task): Unit

  def filterByType(taskType: String): Array[Task]

  def getTasksByDate(date: Date): Array[Task]

  def listAllTasks(): Array[Task]

  def getNextWeekTasks(): Array[Task]
  
  def priorityon(task:Task):Unit
  
  def statusupdate(task:Task):Unit
  
  def durationupdate(task:Task, newduration:Duration):Unit

  //todo: add one method with hard functionality by yourself Done
}

class Task {
  var date: Date =_
  var duration: Duration =_  //todo: try to alter String with Duration type Done
  var taskType: String =_
  var title: String =_
  var note: String =_
  var status: Boolean =_
  var priority = false

  //todo: need to add update functions for duration and status Done
}

  //todo: nice to be* (try to alter with Enumeration) Done
class TaskType {
  val WORK = "work"
  val STUDY = "study"
  val SPORT = "sport"
}

object TaskType extends Enumeration {
  type TaskType = Value
  val work,study, sport = Value
}