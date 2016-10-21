package com.nixsolutions;

import java.util.Random;

public class Task1 {

	static Random r;
	
	public static void main(String[] args) {
		
		r = new Random();
		
		long[][] timeArr = new long[20][2];
		long timeStart;
		
		for (int i = 0; i < timeArr.length; i++) {			
			int[] arr = generateArray(100000);
			System.out.println("Original array sorted: " + Arrays.checkIsSorted(arr));
			
			timeStart = System.currentTimeMillis();			
			Arrays.sort(arr);			
			timeArr[i][0] = System.currentTimeMillis() - timeStart;
			
			timeStart = System.currentTimeMillis();			
			java.util.Arrays.sort(arr);			
			timeArr[i][1] = System.currentTimeMillis() - timeStart;
			
			System.out.println("Sort 2: " + Arrays.checkIsSorted(arr));
		}
		
		for (int i = 0; i < timeArr.length; i++) {			
			System.out.println("Sort " + (i + 1));
			System.out.println("  com.nixsolutions.Arrays.sort: " + timeArr[i][0] + "ms");
			System.out.println("  java.util.Arrays.sort: " + timeArr[i][1] + "ms");
			System.out.println("  average time: " + (timeArr[i][0] + timeArr[i][1]) / 2 + " ms");
		}
	}

	public static int[] generateArray(int length) {
		
		return r.ints(length, -100, 101).toArray();
	}
}
