package com.nixsolutions;

/**
 * Represents base class for writable objects.
 * 
 * @author plakhotnichenko
 */
public class Pen extends Writable {
	
	/**
	 * Sets the ink consumption	
	 */
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
