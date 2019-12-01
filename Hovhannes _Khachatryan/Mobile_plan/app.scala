package hovo

object app {
  def main(args:Array[String]){
    var app = new FinalApp
    app.addTariff
    app.addTariff
    app.addUser
    app.suggestPlan
  }
}
