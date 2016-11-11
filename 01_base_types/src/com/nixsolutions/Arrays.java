package com.nixsolutions;

public class Arrays {

	public static int[] sort(int[] inArr) {		
		int[] workArr = java.util.Arrays.copyOf(inArr, inArr.length);
		    	
    	for (int i = 0; i < workArr.length - 1; i++) {	    		 
            for (int j = 1; j < workArr.length - i; j++) {
                if (workArr[j - 1] > workArr[j]){
                    int temp = workArr[j - 1];
                    workArr[j - 1] = workArr[j];
                    workArr[j] = temp;
                }
            }
	    } 
    	
    	return workArr;
	}
}
