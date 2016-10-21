package com.nixsolutions;

public class Task1 {

	public static void main(String[] args) {
		String str = "You know nothing Jon Snow";
		
		char[] rev = new char[str.length()];
		
		for (int i = str.length() - 1, j = 0; i >= 0 && j < rev.length; i--, j++) {
			rev[j] = str.charAt(i);
		}
		
		System.out.println(new String(rev));
	}
}
