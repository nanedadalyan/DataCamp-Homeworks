package home
import scala.concurrent.duration.Duration
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat,DateTimeFormatter}

object Main extends App {
  var todoList = new ToDo()
  var task1 = new Task
  task1.date = new DateTime()
  task1.duration = Duration(2, "hour")
  task1.status = Status.Not_Done
  task1.note = "will be done ASAP"
  task1.title = "Task_1"
  task1.taskType = TaskType.Work
  todoList.insertTask(task1)

  var workTasks: Array[Task] = todoList.filterByType(TaskType.Work)
  workTasks.foreach(it => {println("Title is: " +it.title)})
  task1.updateDuration(Duration(30,"minutes"))
  println("task1 duration after update is "+task1.duration)
  task1.updateStatus(Status.Partially_Done)
  println("task1 status after update is "+task1.status)
  println(todoList.listAllTasks.mkString(" "))
  todoList.getTasksByDate(DateTime.parse("11/28/2019", DateTimeFormat.forPattern("MM/dd/yyyy")))
  println("Next week's tasks are  "+ (todoList.getNextWeekTasks).mkString(" "))
  todoList.percentDone()
  
}

