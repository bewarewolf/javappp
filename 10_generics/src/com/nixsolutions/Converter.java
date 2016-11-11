package com.nixsolutions;


public interface Converter<I, T> {
  T get(I input);
  
  public class FloatDoubleConverter implements Converter<Float, Double> {

    @Override
    public Double get(Float input) {
      
      return input.doubleValue();
    }
  }
  
  public class IntArrStringConverter implements Converter<Integer[], String> {

    @Override
    public String get(Integer[] input) {
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < input.length; i++) {
        sb.append(input[i]);
        
        if (i != input.length - 1) {
          sb.append(' ');
        }
      }
      
      return sb.toString();
    }
    
  }
}
