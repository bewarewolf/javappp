package com.nixsolutions;

import java.util.Arrays;
import java.util.Random;

public class Main {

	private static StringBuilder tmp;
	
	static {
	    tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	        tmp.append(ch);
	      for (char ch = 'a'; ch <= 'z'; ++ch)
	        tmp.append(ch);
	}
	
	public static void main(String[] args) {
		Writable[] writers = generateWritableArray();
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < writers.length; j++) {
				int symCount = r.nextInt(2) + 3;
				
				for (int k = 0; k < symCount; k++) {					
					writers[j].write(sb, tmp.charAt(r.nextInt(tmp.length())));
				}
				
				writers[j].erase(sb);
			}
		}
		
		Arrays.sort(writers);
		
		for (int j = 0; j < writers.length; j++) {
			System.out.println(writers[j]);
		}
	}

	public static Writable[] generateWritableArray() {		
		Writable[] outArr = new Writable[10];
		
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
