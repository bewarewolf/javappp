package com.nixsolutions;

public class Store {

  static int capacity;
  
  public static synchronized void put(int cap) {
    capacity += cap;
  }
  
  public static synchronized int get(int cap) {
    capacity -= cap;
    return cap;
  }
  
  public static synchronized int count() {
    return capacity;
  }
}
