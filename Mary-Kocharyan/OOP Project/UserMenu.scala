trait UserMenu{
  def addProduct(productname:String,productprice:Int):Product
  def addRecipe(recname:String,recingredients:String,recinstructions:String):Recipe
  def myFridgeProducts(my_products:List[Product]):Unit
  def emptyFridge(): Unit
  def favorite(favorite_recipe:String):Unit
  def notFavorite(not_fav_recipe:String):Unit
  def getIngredByName(myrecipe:String): Unit
  def getInstructions(myrecipe:String): Unit
  def amount(myrecipe:Recipe):Int
  def getByAmount(myamount:Int): Unit
}