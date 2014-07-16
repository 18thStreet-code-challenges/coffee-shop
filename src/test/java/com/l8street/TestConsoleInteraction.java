package com.l8street;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class TestConsoleInteraction  implements BaristaConstants {
	private BaristaMain app;
	@Before
	public void setUp()  {
		app = new BaristaMain();
	}
	@Test
	public void testImmediateQuit() {
		MockIO mockIO = new MockIO("q" + EOL);
		BaristaMain.processInput(app, mockIO.getInputStream(), mockIO.getPrintStream());
		String content = mockIO.getOutput();
		String expected = buildFullInventoryListing() + EOL + buildDrinksMenu() + EOL + EXIT_MESG;
		assertEquals(expected, content);
	}
	@Test
	public void testCoffeePurchase() {
		int[] postCoffeInventory = {10,7,9,10,10,10,10,9,10};
		MockIO mockIO = new MockIO("5" + EOL + "q" + EOL);
		BaristaMain.processInput(app, mockIO.getInputStream(), mockIO.getPrintStream());
		String content = mockIO.getOutput();
		String expected = buildFullInventoryListing() + EOL + buildDrinksMenu() + EOL + 
			app.dispensingMessage("Coffee") + EOL +
			buildInventoryListing(postCoffeInventory) + EOL + buildDrinksMenu() + EOL + EXIT_MESG;
		assertEquals(expected, content);
	}
	@Test
	public void testTwoCoffeePurchasesWithRestock() {
		int[] postCoffeInventory = {10,7,9,10,10,10,10,9,10};
		app = new BaristaMain();
		MockIO mockIO = new MockIO("5" + EOL + "r" + EOL + "5" + EOL + "q");
		BaristaMain.processInput(app, mockIO.getInputStream(), mockIO.getPrintStream());
		String content = mockIO.getOutput();
		String expected = buildFullInventoryListing() + EOL + buildDrinksMenu() + EOL + 
			/* '5' entered here */
		app.dispensingMessage("Coffee") + EOL +
			buildInventoryListing(postCoffeInventory) + EOL + buildDrinksMenu() + EOL +
			/* 'r' entered here */
			buildFullInventoryListing() + EOL + buildDrinksMenu() + EOL + 
			/* '5' entered here */
			app.dispensingMessage("Coffee") + EOL +
			buildInventoryListing(postCoffeInventory) + EOL + buildDrinksMenu() + EOL + EXIT_MESG;
		assertEquals(expected, content);
	}
	@Test
	public void testInvalidInput() {
		app = new BaristaMain();
		MockIO mockIO = new MockIO("55" + EOL + "q");
		BaristaMain.processInput(app, mockIO.getInputStream(), mockIO.getPrintStream());
		String content = mockIO.getOutput();
		String expected = buildFullInventoryListing() + EOL + buildDrinksMenu() + EOL + 
			/* '55' entered here */
			app.invalidMessage("55") + EOL + 
			buildFullInventoryListing() + EOL + buildDrinksMenu() + EOL + EXIT_MESG;
		assertEquals(expected, content);
	}
	/*
	 * Create a fully stocked inventory listing.
	 */
	private String buildFullInventoryListing() {
		int[] inventoryUnits = {10,10,10,10,10,10,10,10,10};
		return(buildInventoryListing(inventoryUnits));
	}
	/*
	 * Create an inventory listing with specified numbers of units.
	 */
	private String buildInventoryListing(int[] inventoryUnits) {
		StringBuilder sb = new StringBuilder();
		sb.append(INVENTORY_MESG).append(EOL)
			.append(IngredientsEnum.COCOA.getName()).append(COMMA).append(inventoryUnits[0]).append(EOL)
			.append(IngredientsEnum.COFFEE.getName()).append(COMMA).append(inventoryUnits[1]).append(EOL)
			.append(IngredientsEnum.CREAM.getName()).append(COMMA).append(inventoryUnits[2]).append(EOL)
			.append(IngredientsEnum.DECAF_COFFEE.getName()).append(COMMA).append(inventoryUnits[3]).append(EOL)
			.append(IngredientsEnum.ESPRESSO.getName()).append(COMMA).append(inventoryUnits[4]).append(EOL)
			.append(IngredientsEnum.FOAMED_MILK.getName()).append(COMMA).append(inventoryUnits[5]).append(EOL)
			.append(IngredientsEnum.STEAMED_MILK.getName()).append(COMMA).append(inventoryUnits[6]).append(EOL)
			.append(IngredientsEnum.SUGAR.getName()).append(COMMA).append(inventoryUnits[7]).append(EOL)
			.append(IngredientsEnum.WHIPPED_CREAM.getName()).append(COMMA).append(inventoryUnits[8]);
		return(sb.toString());
	}
	/*
	 * Creates a drinks menu with the prices we expect.
	 */
	private String buildDrinksMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append(MENU_MESG).append(EOL)
			.append("1,Caffe Americano,$3.30,true").append(EOL)
			.append("2,Caffe Latte,$2.55,true").append(EOL)
			.append("3,Caffe Mocha,$3.35,true").append(EOL)
			.append("4,Cappuccino,$2.90,true").append(EOL)
			.append("5,Coffee,$2.75,true").append(EOL)
			.append("6,Decaf Coffee,$2.75,true");
		return(sb.toString());
	}
}
