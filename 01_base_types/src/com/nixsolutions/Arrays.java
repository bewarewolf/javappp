package com.nixsolutions;

public class Arrays {

	public static void sort(int[] inArr) {		
		int[] workArr = java.util.Arrays.copyOf(inArr, inArr.length);
		
		boolean flag = true;   
		
	    while (flag)
	    {	    	
	    	flag= false;   
            for(int j=0; j < workArr.length - 1; j++) {      
            	if (workArr[j] > workArr[j + 1]) {            		
            		int temp = workArr[j];               
                    workArr[j] = workArr[j + 1];
                    workArr[j + 1] = temp;
                    flag = true;            
                } 
            } 
	    } 
	}
}
