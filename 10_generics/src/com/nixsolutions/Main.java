package com.nixsolutions;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Map<String, List<Integer>> intMap = new HashMap<>();
    intMap.put("list 1", Arrays.asList(new Integer[] { 1, 4, 2, 13 }));
    intMap.put("list 2", Arrays.asList(new Integer[] { 5, 5, 3, 13 }));

    Map<String, List<Float>> floatMap = new HashMap<>();
    floatMap.put("list 1", Arrays.asList(new Float[] { 1.2f, 4f, 0.2f, 13f }));
    floatMap.put("list 2", Arrays.asList(new Float[] { 5.2f, 5.1f, 0.3f, 13.2f }));
    floatMap.put("list 3", Arrays.asList(new Float[] { 15.4f, 2.9f, 13.4f, 3.2f }));
    
    System.out.println("Initial int map: " + intMap);
    System.out.println("Sum: " + sum(intMap));
    System.out.println("Initial float map: " + floatMap);
    System.out.println("Sum: " + sum(floatMap));
    
    FloatDoubleConverter fdConv = new FloatDoubleConverter();
    System.out.println("Float to Double: " + fdConv.get(123.45f));
    
    IntArrStringConverter isConv = new IntArrStringConverter();
    System.out.println("Integer[] to String: " + isConv.get(new Integer[] { 1, 2, 3}));
  }

  public static <T extends Number> Map<String, Double> sum(Map<String, List<T>> inMap) {
    Map<String, Double> outMap = new HashMap<>();
    
    for (String key : inMap.keySet()) {
      double sum = 0;
      List<T> inList = inMap.get(key);
      
      for (int i = 0; i < inList.size(); i++) {
        sum += inList.get(i).doubleValue();
      }
      
      outMap.put(key, sum);      
    }
    
    return outMap;
  }  
}
