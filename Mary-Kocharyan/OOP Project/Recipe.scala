class Recipe( val name:String,var ingredients:String,var instructions:String,var favorite:Boolean = false ){
 //This method returns the ingredients of a recipe separately in an Array, which is going to be used in amount computations.
  def ingredientsArray():Array[String]= {
    println(this.ingredients.split(","))
    this.ingredients.split(",")
  }
  def favByName(): String = {
    if (this.favorite == false) {
      return "Not Favorite" }else {
        return "Favorite"
      }
    }
  }


