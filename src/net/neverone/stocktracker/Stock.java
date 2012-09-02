package net.neverone.stocktracker;

//*****************************************************************************
// File: net.neverone.stocktracker.Stock
// Author: Brandon Rice (http://nevern.homeip.net)
//
// Description: Stock class that works in conjunction with the Tracker
//				class in the same package.
//*****************************************************************************

public class Stock {
	
	public String symbol;
	public String name;
	public double value;
	
	public Stock(String symbol, String name, double value) {
		this.symbol = symbol;
		this.name = name;
		this.value = value;
	}
	
	public Stock() {
		this("", "", 0);
	}
	
	public Stock(String symbol) {
		this(symbol, "", 0);
	}
	
	public String toString() {
		return String.format("%-30s %1.2f", name + " (" + symbol + ")", value);
	}

} // end Stock
