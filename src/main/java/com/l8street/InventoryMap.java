package com.l8street;

import java.util.Collection;
import java.util.TreeMap;
/*
 * The InventoryMap is the data structure for the drink ingredients costs and current
 * count in inventory.  It stores a collection of InventoryItems keyed by their 
 * ingredient name.
 * Its data is populated from IngredientsEnum.
*/
public class InventoryMap extends TreeMap<String, InventoryItem> {
	private static final long serialVersionUID = 0L;
	/*
	 * Populate data structure from data in the Enum.
	 */
	public InventoryMap() {
		for (IngredientsEnum i : IngredientsEnum.values()) {
			put(i.getName(), new InventoryItem(i.getName(), i.getUnit_cost(), 10));
		}
	}
	public void restock() {
		for (InventoryItem i : asCollection()) {
			i.setUnits(10);
		}
	}
	public double getCost(IngredientsEnum e) {
		return get(e.getName()).getCost();
	}
	public int getUnits(IngredientsEnum e) {
		return get(e.getName()).getUnits();
	}
	public boolean sellUnit(DrinkEnum e) {
		return sellUnit(e.getRecipe());
	}
	/*
	 * Try to sell a Drink unit.  If successful (because inventory was adequate),
	 * returns true.
	 */
	public boolean sellUnit(Drink drink) {
		boolean inStock = isMenuItemInStock(drink);
		if (inStock) {
			for (DrinkIngredient i : drink.asCollection()) {
				get(i.getName()).decrementUnits(drink.getIngredientQuantity(i.getName()));
			}
		}
		return(inStock);
	}
	public boolean isMenuItemInStock(Drink drink) {
		boolean succeeded = true;
		for (DrinkIngredient i : drink.asCollection()) {
			succeeded = get(i.getName()).getUnits() >= drink.getIngredientQuantity(i.getName()); 
			if (!succeeded) break;
		}
		return(succeeded);
		
	}
	/*
	 * Allows simpler code for looping through the TreeMap.
	 */
	public Collection<InventoryItem> asCollection() {
		return this.values();
	}
}
