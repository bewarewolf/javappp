package com.nixsolutions;

import static org.reflections.ReflectionUtils.*;
import java.lang.reflect.Field;
import java.util.Set;

public class Utils {
	
	public static Object getPublicValue(MyBean myBean, String fldName) 
			throws IllegalArgumentException, IllegalAccessException {		
		try {			
			Field fld = myBean.getClass().getDeclaredField(fldName);
			fld.setAccessible(true);
			
			return getValueIfAnnotated(myBean, fld);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Object getPublicValueApacheCommons(MyBean myBean, String fldName) 
			throws IllegalAccessException {
		Set<Field> fldSet = getFields(myBean.getClass(), withName(fldName));
		Field fld = fldSet.toArray(new Field[0])[0];
		fld.setAccessible(true);
		
		return getValueIfAnnotated(myBean, fld);
	}
	
	private static Object getValueIfAnnotated(MyBean bean, Field fld) throws IllegalAccessException {
		if (fld.isAnnotationPresent(Public.class)) {
			return fld.get(bean);
		} else {
			throw new IllegalAccessException("Field " + fld.getName() 
												+ " is not annotated");
		}
	}
}
