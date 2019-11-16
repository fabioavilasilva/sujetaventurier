package com.gfi.sujetaventurier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
	
	static int x = 0;
	static int y = 0;
	static int maxX = 0;
	static int maxY = 0;
	static String[][] map = null;
	static String[] directions = null; 

	public static void main(String[] args) throws Exception {
		initialConfiguration();

		for (int i=0; i<directions.length; i++) {
			String direction = directions[i];
			
			switch (direction) {
			case "N":
				moveNorth();
				break;

			case "S":
				moveSouth();
				break;

			case "E":
				moveEast();
				break;

			case "O":
				moveWest();
				break;

			default:
				break;
			}
		}
		
		System.out.println("The hero finished at: (" + x + "." + y + ")");
	}

	/**
	 * Read map and movement files, and initialize the main variables
	 * 
	 * @throws Exception
	 */
	private static void initialConfiguration() throws Exception {
		ResourceBundle configResource = ResourceBundle.getBundle("com.gfi.sujetaventurier.resources.config");
		
		String mapFilePath = "";
		String movementsFilePath = "";
		
		if (configResource != null) {
			if (configResource.containsKey("map.file")) {
				mapFilePath = configResource.getString("map.file");
			}
			if (configResource.containsKey("movements.file")) {
				movementsFilePath = configResource.getString("movements.file");
			}
		}
		
		if (mapFilePath.isEmpty()) {
			throw new Exception("Map file configuration is missing.");
		}
		
		if (movementsFilePath.isEmpty()) {
			throw new Exception("Movements file configuration is missing.");
		}

		MapBuilder mapBuilder = new MapBuilder();
		
		try {
			map = mapBuilder.buildMapFromFile(mapFilePath);
		} catch (FileNotFoundException e) {
			throw new Exception("Map file is missing.", e);
		}

		maxX = map.length-1;
		maxY = map[maxX].length-1;

		try {
			Scanner fileScanner = new Scanner(new File(movementsFilePath));
			if (fileScanner.hasNextLine()) {
				
				String coordinates[] = fileScanner.next().split("");
				
				x = Integer.valueOf(coordinates[0]);
				y = Integer.valueOf(coordinates[2]);
			
				if (fileScanner.hasNextLine()) {
					directions = fileScanner.next().split("");
				}
				
				fileScanner.close();
			}
		} catch (FileNotFoundException e) {
			throw new Exception("Movements file is missing.", e);
		}
		
		if (directions == null) {
			throw new Exception("Movements file is not complete. Fill out all the informations and try again.");
		}
	}

	/**
	 * Move the hero to north (up) until find a empty spot at the map. 
	 * If reach the top edge, move west (left).
	 * If reach the northwest corner (top, left), stop moving.
	 */
	private static void moveNorth() {
		x--;
		
		if (x < 0) {
			x = 0;
			if (x == 0 && y == 0) {
				return;
			}
			moveWest();
		}
		
		while (!map[x][y].equals(" ")) {
			moveNorth();
		}
	}

	/**
	 * Move the hero to south (down) until find a empty spot at the map. 
	 * If reach the down edge, move east (right)
	 * If reach the southeast corner (down, right), stop moving.
	 */
	private static void moveSouth() {
		x++;
		
		if (x > maxX) {
			x = maxX;
			if (x == maxX && y == maxY) {
				return;
			}
			moveEast();
		}
		
		while (!map[x][y].equals(" ")) {
			moveSouth();
		}
	}

	/**
	 * Move the hero to east (right) until find a empty spot at the map. 
	 * If reach the right edge, move south (down)
	 */
	private static void moveEast() {
		y++;
		
		if (y > maxY) {
			y = 0;
			moveSouth();
		}
		
		while (!map[x][y].equals(" ")) {
			moveEast();
		}
	}

	/**
	 * Move the hero to west (left) until find a empty spot at the map. 
	 * If reach the left edge, move north (up)
	 */
	private static void moveWest() {
		y--;
		
		if (y < 0) {
			y = maxY;
			moveNorth();
		}
		
		while (!map[x][y].equals(" ")) {
			moveWest();
		}
	}

}
