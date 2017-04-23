package medium

import org.scalatest.{BeforeAndAfterEach, FunSpec, Matchers}
import okhttp3.mockwebserver.{MockResponse, MockWebServer}
import medium.domainObjects.PostRequest

class MediumClientSpec extends FunSpec with Matchers with BeforeAndAfterEach {
  
  var server: MockWebServer = _

  override protected def beforeEach(): Unit = {
  	server = new MockWebServer()
  }

  override protected def afterEach(): Unit = {
  	server.shutdown()
  }

  describe("MediumClientSpec") {
  	val json = 
  	  """
        |{
        |  "data": {
        |    "id": "123",
        |    "username": "shekhargulati",
        |    "name": "Shekhar Gulati",
        |    "url": "https://medium.com/@shekhargulati",
        |    "imageUrl": "https://cdn-images-1.medium.com/fit/c/200/200/1*pC-eYQUV-iP2Y10_LgGvwA.jpeg"
        |  }
        |}
      """.stripMargin

    server.enqueue(new MockResponse()
      .setBody(json)
      .setHeader("Content-Type", "application/json")
      .setHeader("charset", "utf-8"))
    server.start()

    val medium = new MediumClient("test_client_id", "test_client_secret", 
    	Some("access_token")) {
        override val baseApiUrl = server.url("/v1/me")
    }
    
    val user = medium.getUser
    user should have(
      'id ("123"),
      'username ("shekhargulati"),
      'name ("Shekhar Gulati"),
      'url ("https://medium.com/@shekhargulati"),
      'imageUrl ("https://cdn-images-1.medium.com/fit/c/200/200/1*pC-eYQUV-iP2Y10_LgGvwA.jpeg")
      )
  }
}