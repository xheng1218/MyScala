package datamodel

import org.scalatest.{BeforeAndAfterEach, FunSpec, Matchers}
import java.time.LocalDateTime
import scala.concurrent._
import scala.concurrent.duration._

import slick.driver.H2Driver.api._
import datamodel.DataModel.Task


class DataModelSpec extends FunSpec with Matchers with BeforeAndAfterEach {
  
  var db: Database = _

  override protected def beforeEach(): Unit = {
  	db = Database.forConfig("taskydb")
  	Await.result(db.run(DataModel.createTaskAction), 2 seconds)
  }

  override protected def afterEach(): Unit = Await.result(db.shutdown, 2 seconds)

  describe("DataModel Spec") {

    it("should insert single task into database") {
      val result = Await.result(db.run(DataModel.insertTaskAction(
      	Task(title="Learn Slick", dueBy=LocalDateTime.now().plusDays(1)))), 2 seconds)

  	  result should be(Some(1))
  	}

    it("should insert multiple tasks into database") {
      val tasks = Seq(
        Task(title="Learn Slick", dueBy=LocalDateTime.now().plusDays(1)),
        Task(title="Write blog on Slick", dueBy=LocalDateTime.now().plusDays(2)),
        Task(title="Build a simple application using Slick", dueBy=LocalDateTime.now().plusDays(3))
      )
      val result = Await.result(db.run(DataModel.insertTaskAction(tasks: _*)), 2 seconds)  // _* to ensure every task is an independent function argument
      result should be(Some(3))
    }
  }
}
