package com.nixsolutions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

	static final String PATH_TO_CLASS = "D:\\git\\homework\\javappp\\06_collections\\bin";
	
	public static void main(String[] args) throws ClassNotFoundException {
		MyBean bean = new MyBean();
		
		Field[] fldArr = bean.getClass().getDeclaredFields();
		
//		for (Field fld : fldArr) {
//			try {
//				String fldName = fld.getName(); 
//				System.out.println(fldName + " value: " + Utils.getPublicValue(bean, fldName));
//			} catch (Exception ex) {
//				System.out.println("Exception: " + ex.getMessage());
//			}
//		}
		
		
		for (Field fld : fldArr) {
			try {
				String fldName = fld.getName(); 
				System.out.println(fldName + " value: " + Utils.getPublicValueApacheCommons(bean, fldName));
			} catch (Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
		}
		
		MyClassLoader clLoad = new MyClassLoader(ClassLoader.getSystemClassLoader());
		clLoad.setPath(PATH_TO_CLASS);
		Class<?> cl = clLoad.loadClass("com.nixsolutions.MyCollection");
		
		Method[] methArr = cl.getDeclaredMethods();
		
		for (Method meth : methArr) {
			meth.setAccessible(true);
			System.out.println(meth.getName());
		}
	}
}
