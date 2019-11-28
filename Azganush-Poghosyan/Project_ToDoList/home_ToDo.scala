package home
import org.joda.time.DateTime

class ToDo {
  var todoList: Array[Task] = Array()
  def insertTask(task: Task): Unit = {
    todoList = todoList :+ task
  }
  def filterByType(taskType: TaskType.Value): Array[Task] = {
    todoList.filter(_.taskType == taskType )
  }
  def getTasksByDate(date: DateTime): Array[Task] = {
    todoList.filter(_.date == date)
  }
  def listAllTasks: Array[Task] = {
    todoList
  }
  def getNextWeekTasks:Array[Task] = {
    var date_next_Monday = new DateTime().plusDays(7-new DateTime().getDayOfWeek+1)
    var date_next_week_Sunday = date_next_Monday.plusDays(6)
    todoList.filter(_.date.compareTo(date_next_Monday)>0).filter(_.date.compareTo(date_next_week_Sunday)<0)
  }

  def percentDone():Unit = {
    var rate:Double = todoList.count(_.status == Status.Partially_Done)*0.5 + todoList.count(_.status == Status.Done)*1.0
    var done:Double = ((rate/todoList.length)*100).toInt
    println(done+"%"+" of tasks are done")
  }
}
