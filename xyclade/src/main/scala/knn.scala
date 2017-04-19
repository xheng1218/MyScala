import java.io.File
// import scala.swing.SimpleSwingApplication


object KNNExample {

  def main(args: Array[String]): Unit = {
  	val projectHome = "/Users/xin.heng/dev/xheng1218/MyScala/xyclade"
  	val basePath = projectHome + "/data/KNN_Example_1.csv"
  	val testData = getDataFromCSV(new File(basePath))

  	// val cv = new CrossValidation(testData._2.length, validationRounds=2)

  	val testDataWithIndices = (testData._1.zipWithIndex,
  							   testData._2.zipWithIndex)

  	//val trainingDPSets = cv.train.map(indexList => indexList).
  	//    map(index => testDataWithIndices._1.collectFirst {
  	//    	case (dp, `index`) => dp}.get)
  	println("\nALL DONE")

  }
  /*
  def top = new MainFrame {
  	title = "KNN Exmample"
  	val projectHome = "/Users/xin.heng/dev/xheng1218/MyScala/xyclade"
  	val basePath = projectHome + "/data/KNN_Example_1.csv"
  	val testData = getDataFromCSV(new File(basePath))

  	val plot = ScatterPlot.plot(testData._1, testData._2,
  		'@', Array(Color.red, Color.blue))
  	peer.setContentPane(plot)
  	size = new Dimension(400, 400)
  }
  */
  
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
