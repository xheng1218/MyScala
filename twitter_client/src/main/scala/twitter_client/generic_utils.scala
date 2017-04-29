
package twitter_client

import twitter4j._


object LoginUtils {
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey("Ikj6eHQrocUBTwzy8ybcE8dvu")
    .setOAuthConsumerSecret("3BvgKy9QWlfy0DDYLfbPAiy2sg0XhMgYbb42uhMExW81fB6yBS")
    .setOAuthAccessToken("2358942756-kEvbfaSnsGTa2hbLlWYaNnPc9Gw1FPYGLTniPQN")
    .setOAuthAccessTokenSecret("0qZF722MpA9xZLjsxUUpxXtzKoub3g9VRklP1Bv1Up1F4")
    .build
  
  /**
  Status listener that is attached to Twitter object
  */
  def simpleStatusListener = new twitter4j.StatusListener() {
    def onStatus(status: twitter4j.Status) {println(status.getText)}
    def onDeletionNotice(statusDeletionNotice: twitter4j.StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) {ex.printStackTrace}
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: twitter4j.StallWarning) {}
  }
}


object TwitterInstance {
  val twitter = new twitter4j.TwitterFactory(LoginUtils.config).getInstance()
  
  val twitterStream = new twitter4j.TwitterStreamFactory(LoginUtils.config).getInstance()
  twitterStream.addListener(LoginUtils.simpleStatusListener)
}


