package com.nixsolutions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Main {

	static Random r;
	
	public static void task1(int iterations, int arraySize) {
		r = new Random();
		
		long[][] timeArr = new long[iterations][2];
		long timeStart;
		
		for (int i = 0; i < timeArr.length; i++) {			
			int[] arr = generateArray(arraySize, -100, 100);
			
			timeStart = System.currentTimeMillis();			
			Arrays.sort(arr);			
			timeArr[i][0] = System.currentTimeMillis() - timeStart;
			
			timeStart = System.currentTimeMillis();			
			java.util.Arrays.sort(arr);			
			timeArr[i][1] = System.currentTimeMillis() - timeStart;
		}
		
		for (int i = 0; i < timeArr.length; i++) {			
			System.out.println("Sort " + (i + 1));
			System.out.println("  com.nixsolutions.Arrays.sort: " + timeArr[i][0] + "ms");
			System.out.println("  java.util.Arrays.sort: " + timeArr[i][1] + "ms");
			System.out.println("  average time: " + (timeArr[i][0] + timeArr[i][1]) / 2 + " ms");
		}
	}
	
	public static void task2() throws IOException {
		Scanner keyboard = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter the decimal number: ");		
			String input = keyboard.nextLine();
			
			if ("exit".equals(input)) {
				break;
			} 
			
			double formatNum;			
			try {
				formatNum = Double.parseDouble(input);
			
				if (input.toUpperCase().contains("E")) {
					System.out.println("You entered: " + formatNum);
				} else {				
					DecimalFormat df = new DecimalFormat("0.000E0");
					System.out.println("You entered: " + df.format(formatNum));
				}
			} catch (IllegalArgumentException ex) {
				System.out.println("Invalid input");
				continue;
			}
		}
		
		keyboard.close();
	}
	
	public static void main(String[] args) throws IOException {
		task1(20, 10);
		task2();
	}

	public static int[] generateArray(int length, int minVal, int maxVal) {
		
		return r.ints(length, minVal, maxVal + 1).toArray();
	}
}
