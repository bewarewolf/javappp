package com.nixsolutions;

public class Arrays {

	public static void sort(int[] inArr) {		
		int[] workArr = java.util.Arrays.copyOf(inArr, inArr.length);
		
		for (int i = 0; i < workArr.length - 1; i++) {
			for (int j = i + 1; j < workArr.length; j++) {
				if (workArr[i] > workArr[j]) {					
					int tmp = workArr[i];
					workArr[i] = workArr[j];
					workArr[j] = tmp;
				}
			}
		}
		
		System.out.println("Sort 1: " + Arrays.checkIsSorted(workArr));
	}
	
	public static boolean checkIsSorted(int[] inArr) {		
		for (int i = 0; i < inArr.length - 1; i++) {
			if (inArr[i] > inArr[i + 1]) {
				return false;
			}
		}
		
		return true;
	}
}
