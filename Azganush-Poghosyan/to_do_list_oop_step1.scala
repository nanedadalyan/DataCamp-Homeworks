import java.util.{Calendar, Date}
import Status.Status
import TaskType.TaskType

import scala.concurrent.duration.Duration
object HelloScala extends App {
  var task1 = new Task
  task1.date = new Date()
  task1.duration = Duration(2, "hour")
  task1.status = Status.Not_Done
  task1.note = "will be done ASAP"
  task1.title = "Task_1"
  task1.taskType = TaskType.Work

  var todoList = new ToDo()
  todoList.insertTask(task1)

  var foundedTasks: Array[Task] = todoList.filterByType(TaskType.Work)

  foundedTasks.foreach(it => {
    println("Title is: " +it.title)
  })

  task1.updateDuration(Duration(30,"minutes"))
  println("task1 duration after update is "+task1.duration)
  task1.updateStatus(Status.Partially_Done)
  println("task1 status after update is "+task1.status)
  println(todoList.listAllTasks.mkString(" "))

}

  class ToDo {
    var todoList: Array[Task] = Array()

    def insertTask(task: Task): Unit = {
      todoList = todoList :+ task
    }

    def filterByType(taskType: TaskType.Value): Array[Task] = {
      todoList.filter(_.taskType == taskType )
    }

    def getTasksByDate(date: Date): Array[Task] = {
      todoList.filter(_.date == date)
    }

    def listAllTasks: Array[Task] = {
      todoList
    }
    def partDone():String = {
      val rate = todoList.foreach(it => {if (it.status == Status.Not_Done) 0 else if (it.status == Status.Partially_Done) 0.5
      else 1})
      //val done = ((rate.sum/all.sum)*100).toString
    }
    /**def getNextWeekTasks:Array[Task] = {      //problem with date type
      val now = Calendar.getInstance.getTime
      val dayweek = now.toString.slice(0,3)
      val nextMonday = Date
      if (dayweek=="Mon") {nextMonday=now+7}
      else if (dayweek=="Tue")
      else if (dayweek=="Tue")
      else if (dayweek=="Tue")
      else if (dayweek=="Tue")
      else if (dayweek=="Tue")
      else if (dayweek=="Tue")
    }*/

    //todo: add one method with hard functionality by yourself

//show percentage:what percent of weekly tasks is done = (if task.status==not_done => 0, if partially => 0.5, if
    //done => 1)*
    // weekly tasks (filter tasks from today till Sunday)
  }

  class Task {
    var date: Date =_
    var duration: Duration =_  //todo: try to alter String with Duration type (DONE)
    var taskType: TaskType =_
    var title: String =_
    var note: String =_
    var status: Status =_

    def updateDuration(new_duration:Duration ):Unit = {
      this.duration = new_duration
    }
    def updateStatus(new_status:Status.Value):Unit = {
      this.status = new_status
    }

  }

  //todo: need to add update functions for duration and status (DONE)


//todo: nice to be* (try to alter with Enumeration) (DONE)
object TaskType extends Enumeration {
  type TaskType = Value
  val Work, Study, Sport = Value
}

object Status extends Enumeration {
  type Status = Value
  val Not_Done, Partially_Done, Done = Value
}