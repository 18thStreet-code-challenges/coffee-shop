package com.l8street;
/*
 * DrinkEnum is where the drink recipe data is entered.  
 */
public enum DrinkEnum {
	COFFEE (new Drink("Coffee", 
				new DrinkIngredient(IngredientsEnum.COFFEE, 3),
				new DrinkIngredient(IngredientsEnum.SUGAR, 1),
				new DrinkIngredient(IngredientsEnum.CREAM, 1))),
	DECAF_COFFEE (new Drink("Decaf Coffee",  
					new DrinkIngredient(IngredientsEnum.DECAF_COFFEE, 3),
					new DrinkIngredient(IngredientsEnum.SUGAR, 1),
					new DrinkIngredient(IngredientsEnum.CREAM, 1))),
	CAFFE_LATTE (new Drink("Caffe Latte",  
					new DrinkIngredient(IngredientsEnum.ESPRESSO, 2),
					new DrinkIngredient(IngredientsEnum.STEAMED_MILK, 1))),
	CAFFE_AMERICANO (new Drink("Caffe Americano",  
						new DrinkIngredient(IngredientsEnum.ESPRESSO, 3))),
	CAFFE_MOCHA (new Drink("Caffe Mocha",  
					new DrinkIngredient(IngredientsEnum.ESPRESSO, 1),
					new DrinkIngredient(IngredientsEnum.COCOA, 1),
					new DrinkIngredient(IngredientsEnum.STEAMED_MILK, 1),
					new DrinkIngredient(IngredientsEnum.WHIPPED_CREAM, 1))),
	CAPPUCCINO (new Drink("Cappuccino",  
					new DrinkIngredient(IngredientsEnum.ESPRESSO, 2),
					new DrinkIngredient(IngredientsEnum.STEAMED_MILK, 1),
					new DrinkIngredient(IngredientsEnum.FOAMED_MILK, 1)));
	private Drink recipe;

	private DrinkEnum(Drink recipe) {
		this.recipe = recipe;
	}
	/*
	 * Static because a test needs it
	 */
	public static String getName(DrinkEnum e) {
		return e.recipe.getName();
	}
	public Drink getRecipe() {
		return recipe;
	}
	
}
