package com.nixsolutions.university.util;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

public class LocalDateEditor extends PropertyEditorSupport {

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(LocalDate.parse(text));
  }
  
  @Override
  public String getAsText() {
    Object val = getValue();
    return val == null ? "" : ((LocalDate) getValue()).toString();
  }
}
