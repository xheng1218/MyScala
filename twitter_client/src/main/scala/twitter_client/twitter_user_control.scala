/**
*
* http://bcomposes.com/2013/02/26/using-twitter4j-with-scala-to-perform-user-actions/
*/
package twitter_client

import twitter4j._


trait RateChecker {  
  def checkAndWait(response: twitter4j.TwitterResponse, 
  	               verbose: Boolean = false) {
  	val rateLimitStatus = response.getRateLimitStatus
  	if (verbose) println("Rate limit status: " + rateLimitStatus)

  	if (rateLimitStatus != null && rateLimitStatus.getRemaining == 0) {
  		println("*** You have hit your rate limit ***")

  		val waitTime = rateLimitStatus.getSecondsUntilReset + 10
  		println("Waiting " + waitTime + " seconds ( " + waitTime/60.0 + " minutes) for rate limit reset.")
		Thread.sleep(waitTime*1000)
	}
  }
}
