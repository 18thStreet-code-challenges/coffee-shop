package com.l8street;
/*
 * An InventoryItem is an ingredient (such as cream) with a number of units of that
 * ingredient in inventory.  That number changes as units are sold.
 */
public class InventoryItem {
	private String name;
	private double cost;
	private int units;
	
	public InventoryItem(String name, double cost, int units) {
		super();
		this.name = name;
		this.cost = cost;
		this.units = units;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public void decrementUnits(int num) {
		this.units -= num;
		if (this.units < 0) {
			// Due to logic in IngredientMap.sellUnit(Recipe) it should never be 
			// possible to get here.
			throw new RuntimeException("Application error allowed decrement below zero");
		}
	}

}
