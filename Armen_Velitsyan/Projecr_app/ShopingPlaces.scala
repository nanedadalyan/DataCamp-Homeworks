package FinReport


class ShopingPlaces extends Places {
  override var place_rate = Map("" -> Array[Int](0))
  override var place_comment = Map(""->Array(""))

  override def ratePlace(place: String, rate: Int): Unit = {
    if (place_rate.contains(place)) {
      place_rate(place) = place_rate(place) :+ rate //<- error with updating Map
    } else {
      place_rate += (place -> rate)
    }
  }

  override def getAvgRate(place: String): Double = {
    var averege_rate = place_rate(place).sum.toDouble / place_rate(place).length
    averege_rate
  }

  override def commentPlace(place: String, comment: Array[String]): Unit = {
    if (place_rate.contains(place)) {
      place_comment(place) = place_comment(place) :+ comment //<- error with updating Map
    } else {
      place_comment += (place -> comment)
    }
  }


  override def getComment(place: String): Array[String] = {
      place_comment(place)
  }
}