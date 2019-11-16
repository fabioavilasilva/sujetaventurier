package com.gfi.sujetaventurier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapBuilder {

	public String[][] buildMapFromFile(String filename) throws FileNotFoundException {
		int rows = 0;
		int cols = 0;

		Scanner fileScanner = new Scanner(new File(filename));

		while (fileScanner.hasNextLine()) {
			rows++;

			String[] lineScanner = fileScanner.nextLine().split("");

			if (lineScanner.length > cols) {
				cols = lineScanner.length;
			}
		}

		String[][] map = new String[rows][cols];

		int row = 0;

		fileScanner = new Scanner(new File(filename));
		while (fileScanner.hasNextLine()) {
			String[] lineScanner = fileScanner.nextLine().split("");

			for (int i = 0; i < lineScanner.length; i++) {
				map[row][i] = lineScanner[i];
			}

			row++;
		}

		fileScanner.close();

		return map;
	}

}
