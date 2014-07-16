package com.l8street;
/*
 * A DrinkIngredient is an ingredient (such as cream) at a specific quantity as needed
 * for a specific drink recipe.  It does not store the amounts of that ingredient in current 
 * inventory; for that, see object InventoryItem.
 */
public class DrinkIngredient {
	private IngredientsEnum ingredient;
	private int quantity;
	public DrinkIngredient(IngredientsEnum ingredient, int quantity) {
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	public IngredientsEnum getIngredient() {
		return ingredient;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getName() {
		return ingredient.getName();
	}
	
	
}
