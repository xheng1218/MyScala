package sentiment_analyzer

import java.util.Properties
import scala.collection.convert.wrapAll._

import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations

import sentiment_analyzer.Sentiment.Sentiment


object SentimentAnalyzer {

  val props = new Properties()
  props.setProperty("annotators", "tokenize, ssplit, parse, sentiment")
  val pipeline = new StanfordCoreNLP(props)
    
  /** Extract the main sentiment given an input 
  */
  def mainSentiment(input: String): Sentiment = Option(input) match {
	  case Some(text) if text.nonEmpty => extractSentiment(text)
	  case _ => throw new IllegalArgumentException("Input cannot be null or empty")
  }

  /** Extract a list of sentiment 
  */
  def sentiment(input: String): List[(String, Sentiment)] = Option(input) match {
	  case Some(text) if text.nonEmpty => extractSentiments(text)
	  case _ => throw new IllegalArgumentException("Input cannot be null or empty")
  }

  private def extractSentiment(text: String): Sentiment = {
	  val (_, sentiment) = extractSentiments(text)
      .maxBy { case(sentence, _) => sentence.length }
	  sentiment
  }

  private def extractSentiments(text: String): List[(String, Sentiment)] = {
	  val annotation: Annotation = pipeline.process(text)
    val sentences = annotation.get(classOf[CoreAnnotations.SentencesAnnotation])
    
    sentences
      .map(sentence => (sentence, 
        sentence.get(classOf[SentimentCoreAnnotations.SentimentAnnotatedTree])))
      .map{case(sentence, tree) => (sentence.toString, 
        Sentiment.toSentiment(RNNCoreAnnotations.getPredictedClass(tree)))}
      .toList
  }
}


/** Sentiment value class
*/
object Sentiment extends Enumeration {
  type Sentiment = Value
  val POSITIVE, NEGATIVE, NEUTRAL = Value

  def toSentiment(sentiment: Int): Sentiment = sentiment match {
  	case x if x == 0 || x == 1 => Sentiment.NEGATIVE
	case 2 => Sentiment.NEUTRAL
	case x if x == 3 || x == 4 => Sentiment.POSITIVE
    }
}