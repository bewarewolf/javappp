package com.nixsolutions;

import java.util.Arrays;

public class Task3 {
	
	public static void main(String[] args) {
		String str = "You kKnow nothing Jon Snowy";
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
}
