import java.io.File


object KNNExample {
  def main(args: Array[String]): Unit = {
  	val projectHome = "/Users/xin.heng/dev/xheng1218/MyScala/xyclade"
  	val basePath = projectHome + "/data/KNN_Example_1.csv"
  	val testData = getDataFromCSV(new File(basePath))
  }

  def getDataFromCSV(file: File): (Array[Array[Double]], Array[Int]) = {
  	val source = scala.io.Source.fromFile(file)
  	val data = source.getLines().drop(1).map(x => getDataFromString(x)).toArray

  	source.close()
  	val dataPoints = data.map(x => x._1)
  	val classifierArray = data.map(x => x._2)
  	return (dataPoints, classifierArray)
  }
  
  /** Split the data string into array 

  @param datastring
  @param splitBy 
  */
  def getDataFromString(dataString: String, splitBy: Char = ',' ): (Array[Double], Int) = {

    //Split the comma separated value string into an array of strings
    val dataArray: Array[String] = dataString.split(splitBy)

    //Extract the values from the strings
    val xCoordinate: Double = dataArray(0).toDouble
    val yCoordinate: Double = dataArray(1).toDouble
    val classifier: Int = dataArray(2).toInt

    //And return the result in a format that can later 
    //easily be used to feed to Smile
    return (Array(xCoordinate, yCoordinate), classifier)
  }
}