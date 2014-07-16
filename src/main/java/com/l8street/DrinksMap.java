package com.l8street;

import java.util.Collection;
import java.util.TreeMap;

/*
 * The DrinksMap is the data structure for all the drink recipes used by the app.
 * It is a collection of Drink objects keyed by their name (e.g. Coffe, Cappuccino, etc.)
 * The data is populated from RecipeEnum.
 * 
 * TreeMap uses natural sort order, so the drinks will automatically be
 * alphabetically sorted.
 */
public class DrinksMap extends TreeMap<String, Drink> {
	private static final long serialVersionUID = 0L;
	/*
	 * Populate data structure from data in the Enum.
	 */
	public DrinksMap() {
		for (DrinkEnum r : DrinkEnum.values()) {
			put(r.getRecipe().getName(), r.getRecipe());
		}
	}
	
	public double getPrice(DrinkEnum e) {
		return get(e.getRecipe().getName()).getPrice(); 
	}
	/*
	 * Allows simpler code for looping through the TreeMap.
	 */
	public Collection<Drink> asCollection() {
		return this.values();
	}
	/*
	 * Users select their choice not by name, but by item number.  Since
	 * the Map for Recipes uses full titles as keys, we need a way to 
	 * retrieve a value by number.
	 */
	public Drink getRecipeByOrdinal(int n) {
		int count = 0;
		for (Drink r : asCollection()) {
			if (count == n) return r;
			++count;			
		}
		return null;
	}
}
