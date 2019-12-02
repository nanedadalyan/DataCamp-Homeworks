import scala.collection.mutable.Map

class User extends UserMenu {
  var products:List[Product] = Nil
  var recipes:List[Recipe] = Nil
  var fridge_products:List[Product] = Nil //This List is used to reduce the amount of products that the user already has.


  override def addProduct (productname:String,productprice:Int):Product ={
    var newProduct = new Product(name = productname, price = productprice )
    this.products = this.products :+ newProduct
    newProduct
  }


  override def addRecipe(recipe_name:String,recipe_ingredients:String,recipe_instructions:String):Recipe = {
    var newRecipe = new Recipe(name = recipe_name, ingredients = recipe_ingredients,instructions = recipe_instructions)
    this.recipes = this.recipes :+ newRecipe
    newRecipe
  }


  override def myFridgeProducts(my_products:List[Product]):Unit ={
    this.fridge_products = this.fridge_products ++ my_products
  }//Note: if you want to add products to this list, they must have the type "Product".



  override def emptyFridge(): Unit ={
    this.fridge_products = Nil
  }//If your fridge is empty again, use this.


  override def favorite(favorite_recipe:String):Unit = {
    for ( i<- 0 until recipes.length) if (
      recipes(i).name == favorite_recipe && recipes(i).favorite == false)  {
      recipes(i).favorite = true
    } else if (recipes(i).name == favorite_recipe && recipes(i).favorite == true){
      println("This recipe is already in Your favorite list")}

  }//Use this to mention a recipe as favorite.

  override def notFavorite(not_fav_recipe:String):Unit = {
    for ( i<- 0 until recipes.length) if (
      recipes(i).name == not_fav_recipe && recipes(i).favorite == true)  {
      recipes(i).favorite = false
    } else if (recipes(i).name == not_fav_recipe && recipes(i).favorite == false){
      println("This recipe is not in Your favorite list")}
  }//Use this to remove the recipe from your favorite list.

  override def getIngredByName(myrecipe:String): Unit ={
    for (i <- 0 until recipes.length) if (
      recipes(i).name == myrecipe)
      println (recipes(i).ingredients)
  }//Prints the ingredients of the dish you entered.

  override def getInstructions(myrecipe:String): Unit = {
    for (i <- 0 until recipes.length) if (
      recipes(i).name == myrecipe)
      println(recipes(i).instructions)
  }//Prints the instructions of the dish you entered.

  override def amount(myrecipe:Recipe):Int ={
    var sum = 0
    var ingred = myrecipe.ingredientsArray()
    var products_to_buy:List[Product] = products diff fridge_products
    for (i <- 0 until ingred.length )
      for (j <- 0 until products_to_buy.length)
        if(products_to_buy(j).name == ingred(i)){
          sum = sum + products_to_buy(j).price
        } else {
          sum = sum + 0
        }
    sum
  }//Computes the total amount you will need to cook this dish(reducing the price of products you already have).


  override def getByAmount(myamount:Int): Unit = {
    var available: List[Recipe] = Nil
    var available_favorite = Map[String,String]()
    for (i <- 0 until recipes.length) {
      if (amount(recipes(i)) <= myamount)  {
        available = available :+ recipes(i)
      }
    }
    available.foreach (dish =>  available_favorite(dish.name) = dish.favByName)
    println (available_favorite)
  }//Prints the dishes available for you and whether they are your favorite.
}