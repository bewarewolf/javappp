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
		
		if (strToModify == null || strToRemove == null) {
			return;
		}
		
		StringBuilder sb = new StringBuilder(strToModify);
		
		for (int i = 0; i < cnt; i++) {
			int index = fromBeginning ? sb.indexOf(strToRemove) : sb.lastIndexOf(strToRemove);
			if (index == -1) {
				return;
			}
			
			sb.deleteCharAt(index);
		}
		
		System.out.println(sb.toString());
	}
	
	public static void deduplicateAndSort(String str) {
		
		StringBuilder sb = new StringBuilder(str);	
		
		for (int i = 0; i < sb.length(); i++) {
			for (int j = i + 1; j < sb.length(); ) {
				if (Character.toLowerCase(sb.charAt(i)) == Character.toLowerCase(sb.charAt(j))) {
					sb.deleteCharAt(j);
				} else {
					 j++;
				}
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
		deduplicateAndSort("FloccinaucinihiliPIlifiCation");
	}
}
