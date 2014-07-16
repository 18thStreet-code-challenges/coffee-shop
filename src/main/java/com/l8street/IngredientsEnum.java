package com.l8street;
/*
 * IngredientsEnum is where the ingredients data (name and cost) are entered.
 */
public enum IngredientsEnum {
	COFFEE ("Coffee", .75),
	DECAF_COFFEE ("Decaf Coffee", .75),
	SUGAR ("Sugar", .25),
	CREAM ("Cream", .25),
	STEAMED_MILK ("Steamed Milk", .35),
	FOAMED_MILK ("Foamed Milk", .35),
	ESPRESSO ("Espresso", 1.1),
	COCOA ("Cocoa", .9),
	WHIPPED_CREAM ("Whipped Cream", 1.0);
	private final String name;
	private final double unit_cost;
	
	private IngredientsEnum(String name, double unit_cost) {
		this.name = name;
		this.unit_cost = unit_cost;
	}

	public String getName() {
		return name;
	}

	public double getUnit_cost() {
		return unit_cost;
	}

}
