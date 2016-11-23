package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Locale;

public class Main {

  public static void task1(int year) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);

    System.out.println("Year: " + year);

    for (int i = Calendar.JANUARY; i <= Calendar.DECEMBER; i++) {

      c.set(Calendar.MONTH, i);
      c.set(Calendar.DAY_OF_MONTH, 1);
      System.out.println("Month: " + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
	  + "; max days: " + c.getActualMaximum(Calendar.DAY_OF_MONTH));
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

    if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && c.get(Calendar.DAY_OF_MONTH) == 13) {
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

    past.setLenient(true);
    
    int years = 0;
    int months = 0;
    int days = past.get(Calendar.DAY_OF_MONTH);
    
    int daysBackup = Math.abs(now.get(Calendar.DAY_OF_MONTH) - past.get(Calendar.DAY_OF_MONTH));
//    past.set(Calendar.DAY_OF_MONTH, 1);
//    past.add(Calendar.MONTH, 1);
    
    while (past.compareTo(now) < 0) {
      past.add(Calendar.DAY_OF_MONTH, 1);  
      days++;
      
      if (days == past.getMaximum(Calendar.DAY_OF_MONTH)) {
	months++;
	days = 0;
      }
      if (months == 11) {
	years++;
	months = 0;
	days = 0;
      }      
    }
    
    days += Math.abs(daysBackup);
    
    System.out.format("%d years, %d months and %d days passed", years, months, days);
  }

  public static void task5() {
    String[] locales = new String[] { "en-CA", "de-DE", "vi-VN", "en-PK" };

    System.out.println("Java 8: ");
    LocalDateTime date = LocalDateTime.now();
    
    for (int i = 0; i < locales.length; i++) {
      DateTimeFormatter format = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
	  .withLocale(Locale.forLanguageTag(locales[i]));

      System.out.println(date.format(format));
    }

    System.out.println("Java 7: ");

    for (int i = 0; i < locales.length; i++) {
      Calendar c = Calendar.getInstance(Locale.forLanguageTag(locales[i]));

      DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT,
	  Locale.forLanguageTag(locales[i]));
      String formattedDateTime = formatter.format(c.getTime());

      System.out.println(formattedDateTime);
    }
  }

  public static void main(String[] args) throws ParseException {
//    task1(2015);
//    task1(2016);
//
//    task2(2016, Calendar.OCTOBER);
//
//    task3("31-Oct-16");
    
    task4("18-Oct-16");

//    task5();
  }
}
