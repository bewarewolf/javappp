package com.nixsolutions;


public interface Converter<I, T> {
  
  T get(I input);
}
