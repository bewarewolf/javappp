package com.nixsolutions;

public class Pen extends Writable {
	
	public Pen() {
		consumption = 1.15f;
	}
	
	@Override
	public void write(StringBuilder sb, char sym) {		
		if (capacity >= consumption) {
			sb.append(sym);
			
			applyConsumption();
		}
	}
}
