package com.l8street;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class TestPurchasing {
	private InventoryMap ingredients;
	@Before
	public void setUp()  {
		ingredients = new InventoryMap();
	}
	/*
	 * Tests that ingredients inventory decrements correctly after purchases.
	 */
	@Test
	public void testCoffeeSellout() {
		ingredients.restock();
		assertTrue(ingredients.sellUnit(DrinkEnum.COFFEE));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.COFFEE));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.SUGAR));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.CREAM));
		assertTrue(ingredients.sellUnit(DrinkEnum.COFFEE));
		assertEquals(4, ingredients.getUnits(IngredientsEnum.COFFEE));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.SUGAR));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.CREAM));
		assertTrue(ingredients.sellUnit(DrinkEnum.COFFEE));
		assertEquals(1, ingredients.getUnits(IngredientsEnum.COFFEE));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.SUGAR));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.CREAM));
		assertFalse(ingredients.sellUnit(DrinkEnum.COFFEE));
	}
	/*
	 * Tests that a drink can't be sold after its ingredients have run out.
	 */
	@Test
	public void testDecafCoffeeSellout() {
		ingredients.restock();
		assertTrue(ingredients.sellUnit(DrinkEnum.DECAF_COFFEE));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.DECAF_COFFEE));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.SUGAR));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.CREAM));
		assertTrue(ingredients.sellUnit(DrinkEnum.DECAF_COFFEE));
		assertEquals(4, ingredients.getUnits(IngredientsEnum.DECAF_COFFEE));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.SUGAR));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.CREAM));
		assertTrue(ingredients.sellUnit(DrinkEnum.DECAF_COFFEE));
		assertEquals(1, ingredients.getUnits(IngredientsEnum.DECAF_COFFEE));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.SUGAR));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.CREAM));
		assertFalse(ingredients.sellUnit(DrinkEnum.DECAF_COFFEE));
	}
	/*
	 * Another test like above.
	 */
	@Test
	public void testCaffeLatteSellout() {
		ingredients.restock();
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.ESPRESSO	));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
		assertEquals(6, ingredients.getUnits(IngredientsEnum.ESPRESSO));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
		assertEquals(4, ingredients.getUnits(IngredientsEnum.ESPRESSO));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
		assertEquals(2, ingredients.getUnits(IngredientsEnum.ESPRESSO));
		assertEquals(6, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
		assertEquals(0, ingredients.getUnits(IngredientsEnum.ESPRESSO));
		assertEquals(5, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertFalse(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
	}
	/*
	 * Tests that inventories can run out as a consequence of various purchases.
	 */
	@Test
	public void testMixedItemsSellout() {
		ingredients.restock();
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.ESPRESSO	));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_AMERICANO));
		assertEquals(5, ingredients.getUnits(IngredientsEnum.ESPRESSO));
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_MOCHA));
		assertEquals(4, ingredients.getUnits(IngredientsEnum.ESPRESSO));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.COCOA));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.WHIPPED_CREAM));
		assertTrue(ingredients.sellUnit(DrinkEnum.CAPPUCCINO));
		assertEquals(2, ingredients.getUnits(IngredientsEnum.ESPRESSO));
		assertEquals(7, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.FOAMED_MILK));
		assertFalse(ingredients.sellUnit(DrinkEnum.CAFFE_AMERICANO));
	}
	/*
	 * Test that inventory is correct after restocking.
	 */
	@Test
	public void testRestocking() {
		ingredients.restock();
		assertTrue(ingredients.sellUnit(DrinkEnum.CAFFE_LATTE));
		assertEquals(8, ingredients.getUnits(IngredientsEnum.ESPRESSO	));
		assertEquals(9, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
		ingredients.restock();
		assertEquals(10, ingredients.getUnits(IngredientsEnum.ESPRESSO	));
		assertEquals(10, ingredients.getUnits(IngredientsEnum.STEAMED_MILK));
	}
	/*
	 * Test that prices format correctly.
	 */
	@Test
	public void testFormattedPrices() {
		assertEquals("$3.30", DrinkEnum.CAFFE_AMERICANO.getRecipe().getFormattedPrice());
		assertEquals("$2.55", DrinkEnum.CAFFE_LATTE.getRecipe().getFormattedPrice());
		assertEquals("$3.35", DrinkEnum.CAFFE_MOCHA.getRecipe().getFormattedPrice());
		assertEquals("$2.90", DrinkEnum.CAPPUCCINO.getRecipe().getFormattedPrice());
		assertEquals("$2.75", DrinkEnum.COFFEE.getRecipe().getFormattedPrice());
		assertEquals("$2.75", DrinkEnum.DECAF_COFFEE.getRecipe().getFormattedPrice());
	}

}
