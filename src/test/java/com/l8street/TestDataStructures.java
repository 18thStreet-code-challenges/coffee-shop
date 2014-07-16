package com.l8street;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestDataStructures extends TestCase {
	private DrinksMap recipes;
	private InventoryMap ingredients;
	@Before
	public void setUp()  {
		recipes = new DrinksMap();
		ingredients = new InventoryMap();
	}
	/*
	 * Check that TreeMap's natural sorting works as expected.
	 */
	@Test
	public void testRecipeOrder() {
		String recipeNames[] = {
			DrinkEnum.getName(DrinkEnum.CAFFE_AMERICANO),
			DrinkEnum.getName(DrinkEnum.CAFFE_LATTE),
			DrinkEnum.getName(DrinkEnum.CAFFE_MOCHA),
			DrinkEnum.getName(DrinkEnum.CAPPUCCINO),
			DrinkEnum.getName(DrinkEnum.COFFEE),
			DrinkEnum.getName(DrinkEnum.DECAF_COFFEE)
		};
		int count = 0;
		for (Drink r : recipes.asCollection()) {
			assertEquals(r.getName(), recipeNames[count++]);
		}
	}
	/*
	 * Check that TreeMap's natural sorting works as expected.
	 */
	@Test
	public void testIngredientOrder() {
		String ingredientNames[] = {
				IngredientsEnum.COCOA.getName(),
				IngredientsEnum.COFFEE.getName(),
				IngredientsEnum.CREAM.getName(),
				IngredientsEnum.DECAF_COFFEE.getName(),
				IngredientsEnum.ESPRESSO.getName(),
				IngredientsEnum.FOAMED_MILK.getName(),
				IngredientsEnum.STEAMED_MILK.getName(),
				IngredientsEnum.SUGAR.getName(),
				IngredientsEnum.WHIPPED_CREAM.getName()
		};
		int count = 0;
		for (InventoryItem i : ingredients.asCollection()) {
			assertEquals(i.getName(), ingredientNames[count++]);			
		}
	}
	/*
	 * Test for correct values of ingredient prices.
	 */
	@Test
	public void testGetCost() {
		assertEquals(0.75, ingredients.getCost(IngredientsEnum.COFFEE));
		assertEquals(0.75, ingredients.getCost(IngredientsEnum.DECAF_COFFEE));
		assertEquals(0.25, ingredients.getCost(IngredientsEnum.SUGAR));
		assertEquals(0.25, ingredients.getCost(IngredientsEnum.CREAM));
		assertEquals(0.35, ingredients.getCost(IngredientsEnum.STEAMED_MILK));
		assertEquals(0.35, ingredients.getCost(IngredientsEnum.FOAMED_MILK));
		assertEquals(1.10, ingredients.getCost(IngredientsEnum.ESPRESSO));
		assertEquals(0.90, ingredients.getCost(IngredientsEnum.COCOA));
		assertEquals(1.00, ingredients.getCost(IngredientsEnum.WHIPPED_CREAM));
	}
	/*
	 * Check that drinks are correctly priced according to ingredients.
	 */
	@Test
	public void testRecipeCosts() {
		assertEquals(recipes.getPrice(DrinkEnum.CAFFE_AMERICANO), 
				3.0 * ingredients.getCost(IngredientsEnum.ESPRESSO));
		assertEquals(recipes.getPrice(DrinkEnum.CAFFE_LATTE), 
				2.0 * ingredients.getCost(IngredientsEnum.ESPRESSO) +
				1.0 * ingredients.getCost(IngredientsEnum.STEAMED_MILK));	
		assertEquals(recipes.getPrice(DrinkEnum.COFFEE), 
				3.0 * ingredients.getCost(IngredientsEnum.COFFEE) +
				1.0 * ingredients.getCost(IngredientsEnum.SUGAR) +
				1.0 * ingredients.getCost(IngredientsEnum.CREAM));	
		assertEquals(recipes.getPrice(DrinkEnum.DECAF_COFFEE), 
				3.0 * ingredients.getCost(IngredientsEnum.DECAF_COFFEE) +
				1.0 * ingredients.getCost(IngredientsEnum.SUGAR) +
				1.0 * ingredients.getCost(IngredientsEnum.CREAM));	
		assertEquals(recipes.getPrice(DrinkEnum.CAFFE_MOCHA), 
				1.0 * ingredients.getCost(IngredientsEnum.ESPRESSO) +
				1.0 * ingredients.getCost(IngredientsEnum.COCOA) +
				1.0 * ingredients.getCost(IngredientsEnum.STEAMED_MILK) +
				1.0 * ingredients.getCost(IngredientsEnum.WHIPPED_CREAM));	
		assertEquals(recipes.getPrice(DrinkEnum.CAPPUCCINO), 
				2.0 * ingredients.getCost(IngredientsEnum.ESPRESSO) +
				1.0 * ingredients.getCost(IngredientsEnum.STEAMED_MILK) +
				1.0 * ingredients.getCost(IngredientsEnum.FOAMED_MILK));	
	}
	/*
	 * Test that a recipe contains the correct ingredients and quantities.
	 */
	@Test
	public void testQuantities() {
		assertEquals(3, 
				DrinkEnum.COFFEE.getRecipe().getIngredientQuantity(IngredientsEnum.COFFEE));
		assertEquals(1, 
				DrinkEnum.COFFEE.getRecipe().getIngredientQuantity(IngredientsEnum.SUGAR));
		assertEquals(1, 
				DrinkEnum.COFFEE.getRecipe().getIngredientQuantity(IngredientsEnum.CREAM));
	}
	

}
