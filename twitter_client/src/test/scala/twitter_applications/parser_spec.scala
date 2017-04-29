package twitter_applications

import org.scalatest.{FlatSpec, FunSpec, Matchers}


class SimpleParserSpec extends FlatSpec with Matchers {

  it should ("correctly init an instance of SimpleParsed") in {
      val parsed = SimpleParsed(1, "example")
      parsed.id should be(1)
      parsed.text should be("example")
  }
  
  it should ("work with basic tweet") in {
    val parser = new SimpleParser()
  	val tweet = """{"id":1, "text":"foo"}"""
  	val parsed = parser.parse(tweet) 
    println(parsed)
    parsed match {
      case Some(parsed) => {
        parsed.id should be(1)
        parsed.text should be("foo")        
      }
      case _ => fail("did not parse tweet")
    }
  }

  it should "reject a non-JSON tweet" in {
    val tweet = """"id":1,"text":"foo""""
    val parser = new SimpleParser()
    parser.parse(tweet) match {
      case Some(parsed) => fail("did not reject a non-JSON tweet")
      case e => e should be(None)
    }
  }
}
