package com.nixsolutions;

public class FloatDoubleConverter implements Converter<Float, Double> {

  @Override
  public Double get(Float input) {
    
    return input.doubleValue();
  }
}
