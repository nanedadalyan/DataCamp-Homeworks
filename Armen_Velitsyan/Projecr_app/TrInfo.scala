package FinReport


case class  TrInfo(var id:Int = (for(i <- 1 to 1000000) yield scala.util.Random.nextInt()).filter(_<0).length,
                  var date:String, var amount:Int, var description: String
                  )
/*This are attributes of transaction which must have id,date,amount and description
 * id is random generated number which must be unique for each transaction
 * date is the date the transaction accrued
 * amount is the amount of spent (or earned) money
 * Description is additional info about each transaction
 */