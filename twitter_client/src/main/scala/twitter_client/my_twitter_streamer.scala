/**
* 
* https://dzone.com/articles/using-twitter4j-scala-access
* http://bcomposes.com/2013/02/26/using-twitter4j-with-scala-to-perform-user-actions/
*/
package twitter_client

import org.slf4j.{Logger, LoggerFactory}
import twitter4j._
import collection.JavaConversions._


class TwitterClient {
  
  val twitter = TwitterInstance.twitter
  val logger = LoggerFactory.getLogger(this.getClass)
  
  /** 
  * Search for a query term 
  */
  def search(queryStr : String) {
    val statuses = twitter.search(new Query(queryTerm)).getTweets()
    statuses.foreach(x => println(x.getText + "\n"))
  }

  def updateStatus(statusStr: String, verbose: Boolean = false) {
    val status = twitter.updateStatus(statusStr)
    if (verbose) {
      println("Successfully updated the status to [" + status.getText() + "].") 
    }
  }

  /**
  * Shows the N most recent tweets from the user's home timeline (tweets by people they follow)  
  */
  def getHomeTimeline(n: Int = 10) {
    val statuses = twitter.getHomeTimeline.take(n)
    for ((x, idx) <- statuses.view.zipWithIndex) println(idx + ": " + x.getText() + "\n")
  }
}


class TwitterStreamClient {

  val twitterStream = TwitterInstance.twitterStream
  val logger = LoggerFactory.getLogger(this.getClass)

  def searchStream(queryStr: String) {
    filterQuery = new twitter4j.FilterQuery()
    filterQuery.track(queryStr)
    twitterStream.filter(filterQuery)
    Thread.sleep(2000)
  }

  def cityStream {
    // val cityBox = Array(-122.75,36.8,-121.75,37.8)
    val cityBox = Array(Array(114.166875d, 22.274286d),
                        Array(114.214683d, 22.351943d))

    val filterQuery = new FilterQuery()
    filterQuery.language("en")
    filterQuery.track("Bieber")
    // filterQuery.locations(cityBox)

    twitterStream.filter(filterQuery)
    Thread.sleep(5000)
  }

}
