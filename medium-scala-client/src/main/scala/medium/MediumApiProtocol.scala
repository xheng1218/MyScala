package medium

import spray.json._
import medium.domainObjects._


object MediumApiProtocol extends DefaultJsonProtocol {
  
  implicit val userFormat = jsonFormat5(User)

  implicit val postRequestFormat = jsonFormat7(PostRequest)

  implicit val postFormat = jsonFormat11(Post)
}
