import scala.io.Source
import scala.math.BigDecimal.RoundingMode

object Assignment2 extends App {
  val filename = "aefi.csv"

  val lines = Source.fromFile(filename).getLines().toList
  val header = lines.head.split(",").map(_.trim)
  val data = lines.tail.map(_.split(",").map(_.trim))

  def getColumnValue(record: Array[String], index: Int): Int = {
    if (index < record.length) {
      try {
        record(index).toInt
      } catch {
        case _: NumberFormatException => 0
      }
    } else {
      0
    }
  }

  //val vaxtypeCounts = data.groupBy(_(1)).mapValues(_.size)
  //val mostCommonVax = vaxtypeCounts.maxBy(_._2)

  //val headacheOccurrences = data.groupBy(_(1)).mapValues(records => {
  //val headacheCounts = records.map(record => getColumnValue(record, 12) + getColumnValue(record, 24)).sum
  //val average = headacheCounts.toDouble / records.size
  //BigDecimal(average).setScale(2, RoundingMode.HALF_UP).toDouble
  //})

  val vomitingOccurrences = data.groupBy(_(1)).mapValues(records => {
    records.map(record => getColumnValue(record, 17) + getColumnValue(record, 29)).sum
  })
  val highestVomitingVax = vomitingOccurrences.maxBy(_._2)

  //println(s"1. The most commonly used vaccination product is: ${mostCommonVax._1} with ${mostCommonVax._2} records.")
  //println(s"2. The average occurrence of headache for each type of vaccination product is: $headacheOccurrences")
  println(s"3. The vaccination type with the highest occurrence of vomiting after the first injection is: ${highestVomitingVax._1} with ${highestVomitingVax._2} occurrences.")
}