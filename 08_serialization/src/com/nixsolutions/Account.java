package com.nixsolutions;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Account implements Externalizable {

	private long id;
	private String name;
	private String role;
	
	public Account() {
		this.id = -1;
		this.name = null;
		this.role = null;
	}
	
	public Account(long id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}
	
	@Override
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		String obj = (String) arg0.readObject();
		String[] fields = obj.split("; ");
		this.id = Long.valueOf(fields[0]);
		this.name = fields[1];
		this.role = fields[2];
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		String str = String.format("%s; %s; %s", this.id, this.name, this.role);
		arg0.writeObject(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
	    if (!(obj instanceof Account)) {
	    	return false;
	    }
	    
	    Account castObj = (Account) obj;
	    
		return this.id == castObj.id 
				&& this.name == null ? castObj.name == null : this.name.equals(castObj.name)
				&& this.role == null ? castObj.role == null : this.role.equals(castObj.role);
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
		hash = (int) (hash * 31 + this.id);
	    hash = hash * 31 + (this.name == null ? 0 : this.name.hashCode());
	    hash = hash * 31 + (this.role == null ? 0 : this.role.hashCode());
	    return hash;
	}	
}
