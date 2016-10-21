package com.nixsolutions;

import java.util.Arrays;
import java.util.Random;

/**
 * Entry point 
 * 
 * @author plakhotnichenko
 */
public class Main {

	/**
	 * Instance of StringBuilder containing alphanumeric characters	
	 */
	private static StringBuilder chars;
	
	static {
		chars = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	    	chars.append(ch);
	      for (char ch = 'a'; ch <= 'z'; ++ch)
	    	  chars.append(ch);
	}
	
	public static void main(String[] args) {
		Writable[] writers = generateWritableArray(10);
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < writers.length; j++) {
				int symCount = r.nextInt(2) + 3;
				
				for (int k = 0; k < symCount; k++) {					
					writers[j].write(sb, chars.charAt(r.nextInt(chars.length())));
				}
				
				writers[j].erase(sb);
			}
		}
		
		Arrays.sort(writers);
		
		for (int j = 0; j < writers.length; j++) {
			System.out.println(writers[j]);
		}
	}

	/**
	 * Generates array of random Writable objects
	 * 
	 * @param   count		count of instances to be generated	
	 *
	 * @return  Writable[]  an array containing instances of {@link Pen}, {@link Pencil} 
	 * 	  					and {@link Marker} objects 
	 */
	public static Writable[] generateWritableArray(int count) {		
		Writable[] outArr = new Writable[count];
		
		Random r = new Random();
		
		for (int i = 0; i < outArr.length; i++) {
			switch (r.nextInt(3)) {
			case 0:
				outArr[i] = new Pen();
				break;
			case 1:
				outArr[i] = new Pencil();
				break;
			case 2:
				outArr[i] = new Marker();
				break;
			}
		}
		
		return outArr;
	}
}
