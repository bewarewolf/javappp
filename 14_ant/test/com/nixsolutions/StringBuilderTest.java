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
  
  @Before
  public void setup() {
    sb = new StringBuilder();
  }
  
  @Test
  public void shouldAppendString() {
    // given
    String str = "new string";
    
    // when
    sb.append(str);
    
    // then
    assertTrue(sb.toString().contains(str));
  }
  
  @Test
  public void shouldDeleteSubstring() {
    // given
    sb.append("string");
    
    // when
    sb.delete(3, 5);
    
    // then
    assertNotEquals("in", sb.toString());
  }
  
  @Test
  public void shouldReturnSubstringForRange() {
    // given
    String strToAppend = "new string";
    sb.append(strToAppend);
    
    // then    
    assertEquals("str", sb.substring(4, 7));
    assertEquals(strToAppend.length(), sb.length());
  }
  
  @Test
  public void shouldReplaceSubstring() {
    // given
    sb.append("some abc string");
    
    // when
    sb.replace(5, 8, "xyz");
    
    // then
    assertEquals("some xyz string", sb.toString());
  }
  
  @Test
  public void shouldReturnCharAtIndex() {
    // given
    sb.append("qwerty");
    
    // then
    assertEquals('q', sb.charAt(0));
    assertEquals('e', sb.charAt(2));
  }
  
  @Test
  public void shouldThrowExceptionForWrongIndex() {
    // given
    ex.expect(IndexOutOfBoundsException.class);
    sb.append("abc");
    
    // when
    sb.charAt(5);
    sb.charAt(-3);
  }
  
  @Test
  public void shouldReturnIndexOfSubstring() {
    // given
    sb.append("some abc string");
    
    // then
    assertEquals(5, sb.indexOf("abc"));
  }
  
  @Test
  public void shouldInsertSubstring() {
    // given
    sb.append("some string");
    
    // when
    sb.insert(5, "abc");
    
    // then
    assertEquals("some abcstring", sb.toString());
  }
  
  @Test
  public void shouldReturnLastIndexOfSubstring() {
    // given
    sb.append("appending string");
    
    // then
    
    assertEquals(13, sb.lastIndexOf("in"));
  }
  
  @Test
  public void shouldReverseString() {
    // given
    sb.append("abcd");
    
    // then
    assertEquals("dcba", sb.reverse().toString());
  }
}
