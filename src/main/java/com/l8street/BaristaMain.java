package com.l8street;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class BaristaMain implements BaristaConstants {
	private DrinksMap recipes = new DrinksMap();
	private InventoryMap inventory = new InventoryMap();	

	public static void main(String[] args) {
		BaristaMain app = new BaristaMain();
		processInput(app, System.in, System.out);
	}
	/*
	 * Main body of the app.  IO is abstracted out to accommodate testing with mock input.
	 * (See TestConsoleInteraction)
	 */
	public static void processInput(BaristaMain app, InputStream inputStream, PrintStream outputStream) {
		outputStream.print(app.createMenu());
		boolean appRunning = true;
	    Scanner in = new Scanner(inputStream);
	    while (appRunning) {
		    int action = EXIT;
		    String userInput = "";
		    if (in.hasNext()) {
		    	userInput = in.nextLine().trim();
		    	action = app.validateSelection(userInput);
		    }
		    StringBuilder sb = new StringBuilder();
	    	switch (action) {
	    		case EXIT:
	    			appRunning = false;
	    			// This was not in the spec, but it's nicer :-)
	    			sb.append(EXIT_MESG);
	    			break;
	    		case RESTOCK:
	    			app.inventory.restock();
	    			sb.append(app.createMenu());
	    			break;
	    		case INVALID:
	    			sb.append(app.invalidMessage(userInput)).append(EOL);
	    			sb.append(app.createMenu());
	    			break;
	    		default:
	    			Drink r = app.recipes.getRecipeByOrdinal(action - 1);
	    			if (app.inventory.sellUnit(r)) {
	    				sb.append(app.dispensingMessage(r.getName())).append(EOL);
	    			}
	    			else {
	    				sb.append(app.outOfStockMessage(r.getName())).append(EOL);
	    			}
	    			sb.append(app.createMenu());
	    	}
	    	outputStream.print(sb.toString());
	    }
	    in.close();
	}
	/*
	 * Translates user input to action codes.
	 */
	private int validateSelection(String command) {
		String s = command.toLowerCase();
		int result = 0;		
		if (s.equals("r")) {
			result = RESTOCK;
		}
		else if (s.equals("q")) {
			result = EXIT;
		}
		else {
			try {
				result = Integer.parseInt(s);
			}
			catch (NumberFormatException e) {
				// Should not be possible
			}
			if (result < 1 || result > recipes.size()) {
				result = INVALID;
			}
		}
		return result;
	}
	/*
	 * Create combined inventory list and drinks menu.
	 */
	private String createMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append(INVENTORY_MESG).append(EOL).append(buildInventoryListing()).append(EOL);
		sb.append(MENU_MESG).append(EOL).append(buildDrinksMenu()).append(EOL);
		return sb.toString();
	}
	/*
	 * Creates inventory list.
	 */
	private String buildInventoryListing() {
		StringBuilder sb = new StringBuilder();
		int count = 1;
		for (InventoryItem i : inventory.asCollection()) {
			sb.append(i.getName()).append(COMMA).append(i.getUnits());
			if (count++ < inventory.size()) sb.append(EOL);			
		}
		return sb.toString();
	}
	/*
	 * Creates drink menu.
	 */
	private String buildDrinksMenu() {
		StringBuilder sb = new StringBuilder();
		int count = 1;
		for (Drink r : recipes.asCollection()) {
			sb.append(count).append(COMMA).append(r.getName()).append(COMMA)
				.append(r.getFormattedPrice()).append(COMMA)
				.append((inventory.isMenuItemInStock(r) ? "true" : "false"));
			if (count++ < recipes.size()) sb.append(EOL);
		}
		return sb.toString();
	}
	/*
	 * Next three methods:  Messages are generalized into methods to make sure 
	 * that messaging in the app and messages generated in tests correspond.
	 * (see TestConsoleInteraction)
	 */
	public String invalidMessage(String badInput) {
		return INVALID_MESG + badInput;
	}
	public String dispensingMessage(String drinkName) {
		return DISPENSING_MESG + drinkName;
	}
	public String outOfStockMessage(String ingredient) {
		return OUTOFSTOCK_MESG + ingredient;
	}
			
}
