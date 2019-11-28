package home

import Status.Status
import TaskType.TaskType
import org.joda.time.DateTime
import scala.concurrent.duration.Duration

class Task {
  var date: DateTime =_
  var duration: Duration =_
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
