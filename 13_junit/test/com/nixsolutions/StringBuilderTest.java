package com.nixsolutions;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringBuilderTest {

  private StringBuilder sb;
  
  @Rule
  public ExpectedException ex = ExpectedException.none(); 
  
  @Test
  public void shouldAppendString() {
    // given
    sb = new StringBuilder("new");
    
    // when
    sb.append(" string");
    
    // then
    assertEquals("new string", sb.toString());
  }
  
  @Test
  public void shouldDeleteSubstring() {
    // given
    sb = new StringBuilder("string");
    
    // when
    sb.delete(3, 5);
    
    // then
    assertEquals("strg", sb.toString());
  }
  
  @Test
  public void shouldReturnSubstringForRange() {
    // given
    sb = new StringBuilder("new string");
    
    // then    
    assertEquals("str", sb.substring(4, 7));
  }
  
  @Test
  public void shouldReplaceSubstring() {
    // given
    sb = new StringBuilder("some abc string");
    
    // when
    sb.replace(5, 8, "xyz");
    
    // then
    assertEquals("some xyz string", sb.toString());
  }
  
  @Test
  public void shouldReturnCharAtIndex() {
    // given
    sb = new StringBuilder("qwerty");
    
    // then
    assertEquals('q', sb.charAt(0));
    assertEquals('e', sb.charAt(2));
  }
  
  @Test
  public void shouldThrowExceptionForWrongPositiveIndex() {
    // given
    ex.expect(IndexOutOfBoundsException.class);
    sb = new StringBuilder("abc");
    
    // when
    sb.charAt(5);
  }
  
  @Test
  public void shouldThrowExceptionForNegativeIndex() {
    // given
    ex.expect(IndexOutOfBoundsException.class);
    sb = new StringBuilder("abc");
    
    // when
    sb.charAt(-5);
  }
  
  @Test
  public void shouldReturnIndexOfSubstring() {
    // given
    sb = new StringBuilder("some abc string");
    
    // then
    assertEquals(5, sb.indexOf("abc"));
  }
  
  @Test
  public void shouldInsertSubstring() {
    // given
    sb = new StringBuilder("some string");
    
    // when
    sb.insert(5, "abc");
    
    // then
    assertEquals("some abcstring", sb.toString());
  }
  
  @Test
  public void shouldReturnLastIndexOfSubstring() {
    // given
    sb = new StringBuilder("appending string");
    
    // then
    assertEquals(13, sb.lastIndexOf("in"));
  }
  
  @Test
  public void shouldReverseString() {
    // given
    sb = new StringBuilder("abcd");
    
    // then
    assertEquals("dcba", sb.reverse().toString());
  }
}
