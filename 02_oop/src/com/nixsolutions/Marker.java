package com.nixsolutions;

/**
 * Represents Marker object
 * 
 * @author plakhotnichenko
 */
public class Marker extends Writable{

	/**
	 * Sets the ink consumption	
	 */
	public Marker() {
		consumption = 1;
	}
	
	@Override
	public void write(StringBuilder sb, char sym) {	
		if (capacity >= consumption) {
			sb.append(sym);
			
			if (symbolsWritten >= 21 && symbolsWritten <= 40) {
				consumption = 1.09f;
			} else if (symbolsWritten > 40) {
				consumption = 1.21f;
			}
			
			applyConsumption();				
		}		
	}
}
