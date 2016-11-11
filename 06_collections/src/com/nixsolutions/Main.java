package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;

public class Main {

	public static void main (String[] args) {
		System.out.println("Creating collection");
		MyCollection<String> col = new MyCollection<String>();
		col.add("string 1");
		col.add("string 2");
		col.add("string 3");
		col.add("string 4");
		col.add("string 5");
		col.add("string 6");
		col.add("string 7");
		col.add("string 8");
		col.add("string 9");
		
		printCollection(col);
		
		System.out.println("Removing items from collection");
		MyCollection<String> col2 = new MyCollection<String>();
		col2.add("string 1");
		col2.add("string 3");
		col2.add("string 5");		
		
		col.remove("string 2");
		col.removeAll(col2);
		
		printCollection(col);
		
		System.out.println("Retaining items from collection");
		MyCollection<String> col3 = new MyCollection<String>();
		col3.add("string 6");
		col3.add("string 8");
		
		col.retainAll(col3);
		printCollection(col);
		
		System.out.println("Adding a collection");
		
		col.addAll(col2);
		printCollection(col);
		
		System.out.println("Check if contains");
		
		System.out.println(col.contains("string 9"));
		System.out.println(col.contains("string 1"));
		
		MyCollection<String> col4 = new MyCollection<String>();
		col4.add("string 6");
		col4.add("string 10");
		
		System.out.println(col.containsAll(col3));
		System.out.println(col.containsAll(col4));
		
		System.out.println("Clear");
		System.out.println(col.isEmpty());
		col.clear();
		System.out.println(col.isEmpty());
	}
	
	public static void printCollection(Collection<?> col) {
		Iterator<?> it = col.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
