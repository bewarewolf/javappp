package com.nixsolutions;

public class Task2 {

	public static void main(String[] args) {
		String str = "You know nothing Jon Snow";
		
		String rem = "o";
		int remCnt = 3;
		boolean starts = false;
		
		StringBuilder sb = new StringBuilder(str);
		
		for (int i = 0; i < remCnt; i++) {
			int index = starts ? sb.indexOf(rem) : sb.lastIndexOf(rem);
			sb.deleteCharAt(index);
		}
		
		System.out.println(sb.toString());
	}

}
