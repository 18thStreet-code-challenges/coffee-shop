package com.l8street;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 * A Drink is the list of ingredients and their quantities for a drink.
 */
public class Drink {
	private  Map<String, DrinkIngredient> drinkIngredients;
	private String name;
	public Drink(String name, DrinkIngredient... drinkIngredientList) {
		this.name = name;
		drinkIngredients = new HashMap<String, DrinkIngredient>();
		for (DrinkIngredient d : drinkIngredientList) {
			drinkIngredients.put(d.getIngredient().getName(), d);
		}
	}
	public Collection<DrinkIngredient> asCollection() {
		return drinkIngredients.values();
	}
	public int getIngredientQuantity(String ingredientName) {
		return drinkIngredients.get(ingredientName).getQuantity();
	}
	
	public int getIngredientQuantity(IngredientsEnum e) {
		return getIngredientQuantity(e.getName());
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		double sum = 0.0;
		for (DrinkIngredient d : asCollection()) {
			sum += (double)d.getQuantity() * d.getIngredient().getUnit_cost();
		}
		return sum;
	}
	public String getFormattedPrice() {
	    NumberFormat nf = NumberFormat.getCurrencyInstance();
	    return nf.format(getPrice());		
	}
}
