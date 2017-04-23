package datamodel

import java.sql.Timestamp
import java.time.LocalDateTime

import slick.driver.H2Driver.api._

import datamodel.ColumnDataMapper._


object DataModel {

  /** Task class
  */
  case class Task(
    title: String,
    description: String = "",
    createdAt: LocalDateTime = LocalDateTime.now(),
    dueBy: LocalDateTime,
    tags: Set[String] = Set[String](),
    id: Long = 0L
    )
  
  /** TaskTable class
  */
  class TaskTable(tag: Tag) extends Table[Task](tag, "tasks") {
    def title = column[String]("title")
    def description = column[String]("description")
    def createdAt = column[LocalDateTime]("createdAt")(localDateTimeColumnType)
    def dueBy = column[LocalDateTime]("dueBy")(localDateTimeColumnType)
    def tags = column[Set[String]]("tags")(setStringColumnType)
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    /***/
    override def * = (title, description, createdAt, dueBy, tags, id) <> (Task.tupled, Task.unapply)
  }
  
  lazy val Tasks = TableQuery[TaskTable]
  
  val createTaskAction = Tasks.schema.create

  def insertTaskAction(tasks: Task*) = Tasks ++= tasks.toSeq  // ++= is from slick documentary; it gives you an accumulated count

  val listTasksAction = Tasks.result
  
}

/** ColumnDataMapper class
*/
object ColumnDataMapper {
  implicit val localDateTimeColumnType = MappedColumnType.base[LocalDateTime, Timestamp](
    ldt => Timestamp.valueOf(ldt),
    t => t.toLocalDateTime
  )

  implicit val setStringColumnType = MappedColumnType.base[Set[String], String](
    tags => tags.mkString(","),
    tagsString => tagsString.split(",").toSet
  )
}
