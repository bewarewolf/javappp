package com.nixsolutions;

import java.io.Serializable;

public class MyBean implements Serializable {
	
	private static final long serialVersionUID = 2782529298572125354L;

	private String field;
	
	public MyBean(String fld) {
		this.field = fld;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
	    if (!(obj instanceof MyBean)) {
	    	return false;
	    }
	    
	    MyBean castObj = (MyBean) obj;
		return this.field == null 
				? castObj.field == null 
				: this.field.equals(castObj.field);
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
	    hash = hash * 31 + (this.field == null ? 0 : this.field.hashCode());
	    return hash;
	}
}
