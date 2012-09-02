package net.neverone.stocktracker;

//*****************************************************************************
// File: net.neverone.stocktracker.Tracker
// Author: Brandon Rice (http://nevern.homeip.net)
//
// Description: A simple console-based stock tracker originally created as 
//				part of a learning exercise.  It interfaces with Yahoo's
//				financial API to get its data.
//
// Usage: Put stock name abbreviations in a file, "stocks.txt", in the same
//		  directory, and run the program.
//*****************************************************************************

import java.net.*;
import java.io.*;
import java.util.*;

public class Tracker {
	
	private URL url;
	private ArrayList<Stock> stocks;
	
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		
		for (Stock stock: tracker.stocks) {
			System.out.println(stock);
		}
	}
	
	public Tracker() {
		String urlString = "http://download.finance.yahoo.com/d/quotes.csv?s=";
		File file = new File("stocks.txt");
		Scanner fileReader;
		stocks = new ArrayList<Stock>();
		
		try {
			fileReader = new Scanner(file);
			String newSymbol;
			
			while (fileReader.hasNextLine()) {
				stocks.add(new Stock(newSymbol = fileReader.nextLine()));
				urlString += newSymbol + ",";
			}
			
			stocks.trimToSize();
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			System.exit(1);
		}
		
		urlString += "&f=nl1&e=.csv"; // needed to finish off the URL.
		
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			System.err.println("Bad URL.");
			System.exit(1);
		}
		
		update();
	} // end Tracker constructor
	
	public void update() {
		BufferedReader in;
		String inputLine;
		int counter = 0;
		
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			while ((inputLine = in.readLine()) != null) {
				String[] tokenString = inputLine.split(",");
				stocks.get(counter).name = tokenString[0];
				stocks.get(counter).value = Double.parseDouble(tokenString[1]);
				counter++;
			}
			
			in.close();
		} catch (IOException e) {
			System.err.println("I/O Exception while reading.");
			System.exit(1);
		} 
	} // end update
	
} // end Tracker
