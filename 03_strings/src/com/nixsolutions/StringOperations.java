package com.nixsolutions;

import java.util.Arrays;

public class StringOperations {

	public static void reverse(String str) {
		
		char[] rev = new char[str.length()];
		
		for (int i = str.length() - 1, j = 0; i >= 0 && j < rev.length; i--, j++) {
			rev[j] = str.charAt(i);
		}
		
		System.out.println(new String(rev));
	}
	
	public static void remove(String strToModify, String strToRemove, int cnt, boolean fromBeginning) {
		
		StringBuilder sb = new StringBuilder(strToModify);
		
		for (int i = 0; i < cnt; i++) {
			int index = fromBeginning ? sb.indexOf(strToRemove) : sb.lastIndexOf(strToRemove);
			sb.deleteCharAt(index);
		}
		
		System.out.println(sb.toString());
	}
	
	public static void deduplicateAndSort(String str) {
		
		char[] strArr = str.toCharArray();
		StringBuilder sb = new StringBuilder();	
		
		for (char c : strArr) {
			if (!Character.isLetterOrDigit(c)) {
				continue;
			}
			
			int i = 0;			
			boolean contains = false;
			
			do {				
				if (sb.length() > 0 && (
						sb.charAt(i) == Character.toLowerCase(c) || sb.charAt(i) == Character.toUpperCase(c))) {
					contains = true;
					continue;
				}
			} while (++i < sb.length());
			
			if (!contains) {
				sb.append(c);
			}
		}
		
		int[] buff = sb.chars().toArray();
		Arrays.sort(buff);
		
		String result = new String(buff, 0, buff.length);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		reverse("You know nothing Jon Snow");
		remove("You know nothing Jon Snow", "o", 3, true);
		deduplicateAndSort("You kKnow nothing Jon Snowy");
	}
}
