package com.l8street;

public interface BaristaConstants {
	/*  Action codes representing user responses */
	public static final int EXIT = -2;     // When user enters q/Q
	public static final int RESTOCK = -1;  // When user enters r/R
	public static final int INVALID = 0;   // Invalid user entry
	
	public static final String COMMA = ",";
	public static final String EOL = System.getProperty("line.separator");
	
	/* Text messages are all made into variables to ensure that messages used
	 * in testing correspond to those used in the app.
	 */
	public static final String EXIT_MESG = "Exiting.";
	public static final String INVALID_MESG = "Invalid selection: ";
	public static final String DISPENSING_MESG = "Dispensing: ";
	public static final String OUTOFSTOCK_MESG = "Out of stock: ";
	public static final String INVENTORY_MESG = "Inventory:";
	public static final String MENU_MESG = "Menu:";
}
