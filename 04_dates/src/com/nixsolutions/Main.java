package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;

public class Main {
	
	public static void task1(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		
		System.out.println("Year: " + year);
		
		for (int i = Calendar.JANUARY; i <= Calendar.DECEMBER; i++) {
			
			c.set(Calendar.MONTH, i);
			System.out.println("Month: " + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + 
					"; max days: " + c.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
	}
	
	public static void task2(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		
		StringBuilder sb = new StringBuilder(); 
		
		for (int i = c.getActualMinimum(Calendar.DAY_OF_MONTH); i <= c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			c.set(Calendar.DAY_OF_MONTH, i);
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				sb.append(String.valueOf(i) + "; ");
			}
		}
		
		System.out.println("Mondays: " + sb.toString());
	}
	
	public static void task3(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MMM-yy");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY &&
				c.get(Calendar.DAY_OF_MONTH) == 13) {
			System.out.println("TRUE");
		} else {
			System.out.println("FALSE");
		}
	}
	
	public static void task4(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MMM-yy");
		Calendar past = Calendar.getInstance();
		past.setTime(df.parse(date));
		
		Calendar now = Calendar.getInstance();
		
		if (!now.after(past)) {
			System.out.println("Date should be before current");
			return;
		}
		
		final int day = 24 * 60 * 60 * 1000;
		long diffDays = (now.getTimeInMillis() - past.getTimeInMillis()) / day; 
		
		long monthCnt = (diffDays % 365) / 30;
		long yearCnt = diffDays / 365;
		long dayCnt = diffDays - yearCnt * 365 - monthCnt * 30; 

		System.out.println(String.format("%s years, %s months and %s days passed", yearCnt, monthCnt, dayCnt));
	}
	
	public static void task5() {
		String[] locales = new String[] { "en-CA", "de-DE", "vi-VN", "en-PK" };
		
		LocalDate date = LocalDate.now();
		
		for (int i = 0; i < locales.length; i++) {
			DateTimeFormatter format = 
					DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
					.withLocale(Locale.forLanguageTag(locales[i]));
			
			System.out.println(date.format(format));
		}		
	}
	
	public static void main(String[] args) throws ParseException {
		task1(2015); 
		task1(2016);
		
		task2(2016, Calendar.OCTOBER);
	
		task3("13-May-16");
		
		task4("27-Sep-16");
		
		task5();
	}
}
