package FinReport

abstract  class Places {
  /*This class gets rates of each places that user has visited, stores rates
   as arrays  and returnes the average rate for each place */
  var place_rate = Map(""->Array(0))
  var place_comment = Map(""->Array(""))
  def ratePlace(place: String, rate: Int): Unit = {}
  def commentPlace(place: String, rate: String):Unit = {}
  def getAvgRate(place:String):Double = {
    0.0
  }
  def getComment(place:String):Array[String] = {
    Array[String]("")
  }
}
