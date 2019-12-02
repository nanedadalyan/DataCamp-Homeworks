object TestTheApp extends App{
  var mary = new User
  //creating some products and adding them to the "products" list
  var butter = new Product(name = "butter", price = 500)
  var onion = new Product(name = "onion", price = 100)
  var salt = new Product(name = "salt", price = 100)
  var pepper = new Product(name = "pepper", price = 1000)
  var tomato = new Product(name= "tomato", price = 400)
  var cheese = new Product(name= "cheese", price = 500)
  var egg = new Product(name = "egg", price = 60)
  var potato = new Product(name = "potato", price = 250)
  var flour = new Product(name = "flavor", price = 300)
  var oil = new Product(name = "oil", price = 700)
  var sourcream = new Product(name = "sourcream",price = 200)
  mary.products = List(butter, onion, salt,pepper,tomato,cheese,egg,potato,flour,oil,sourcream)
  // creating some recipes and adding them to the "recipes" list
  var tomato_tortelini = new Recipe(name = "Tomato Tortellini Soup",ingredients = "butter,onion,salt,pepper,chicken,tomato,cheese",
    instructions = "Melt the butter in a Dutch oven over medium heat. Add the onion, garlic, salt, and pepper and cook, stirring occasionally, until tender, about 6 minutes.\nStir in the vinegar. Add the crushed tomatoes, broth, cream, and bay leaves, and bring to a boil. Reduce the heat to a simmer and simmer for 5 minutes. Add the tortellini and simmer until cooked through, 5 to 6 minutes more (check at 3 minutes for fresh tortellini). Remove from the heat, remove and discard the bay leaves, and stir in the basil. Ladle into bowls and top with Parmesan cheese and more basil, if desired.")
  var potato_pancake = new Recipe(name= "Mashed Potato Pancakes", ingredients = "cheese,egg,potato,flour,oil", instructions ="Grate 1 ounce Parmesan cheese (about 1/2 cup) and place in a large bowl. Chop until you have 2 tablespoons fresh chives and add to the bowl. Add 1 large egg and lightly beat with a fork or wooden spoon to combine. Add 2 cups cold mashed potatoes and 1/4 cup of the all-purpose flour, and stir to combine. Refrigerate the mixture for 10 minutes." )
  mary.recipes = List(tomato_tortelini, potato_pancake)

  //adding products and recipes using methods
  mary.addProduct(productname = "olive oil", productprice = 900)
  mary.addRecipe(recipe_name = "Crispy Chicken Nuggets",recipe_ingredients = "egg,pepper,chicken,olive oil,butter", recipe_instructions = "Beat egg, salt, and pepper together in a bowl. Pour crushed cereal into a separate bowl. Coat chicken pieces in egg mixture." )
  //making one of my recipes favorite
  mary.favorite("Crispy Chicken Nuggets")
  //checking what options do we have for this amount of money.
  mary.getByAmount(myamount =9000)
  //Printing ingredients and instructions of a recipe.
  mary.getIngredByName(myrecipe="Crispy Chicken Nuggets")
  mary.getInstructions(myrecipe = "Crispy Chicken Nuggets")
}

