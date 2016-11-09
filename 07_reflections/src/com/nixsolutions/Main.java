package com.nixsolutions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

  static final String PATH_TO_CLASS = "~/git/javappp/06_collections/bin";

  public static void main(String[] args) 
      throws ClassNotFoundException, NoSuchMethodException, SecurityException, 
      IllegalAccessException, IllegalArgumentException {
    // Task 1
    MyBean bean = new MyBean();

    Method[] pubValMethods = new Method[] { 
        Utils.class.getMethod("getPublicValue", MyBean.class, String.class),
        Utils.class.getMethod("getPublicValueGoogleReflections", MyBean.class, String.class)
    };
    
    for (Method meth : pubValMethods) {
      loopFields(bean, meth);
    }

    // Task 2
    MyClassLoader clLoad = new MyClassLoader(ClassLoader.getSystemClassLoader());
    clLoad.setPath(PATH_TO_CLASS);
    Class<?> cl = clLoad.loadClass("com.nixsolutions.MyCollection");

    Method[] methArr = cl.getDeclaredMethods();

    for (Method meth : methArr) {
      meth.setAccessible(true);
      System.out.println(meth.getName());
    }
  }
  
  private static void loopFields(MyBean myBean, Method meth) 
      throws IllegalAccessException, IllegalArgumentException {
    System.out.println(meth.getName());
    Field[] fldArr = myBean.getClass().getDeclaredFields();
    
    for (Field fld : fldArr) {
      try {
        String fldName = fld.getName();
        System.out.println(fldName + " value: " + meth.invoke(Utils.class, myBean, fldName));
      } catch (InvocationTargetException ex) {
        System.out.println("Exception: " + ex.getCause().getMessage());
      } 
    }
  }
}
