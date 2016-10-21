package com.nixsolutions;

/**
 * Represents Pencil object.
 * 
 * @author plakhotnichenko
 */
public class Pencil extends Writable {

	/**
	 * Ink consumption of pencil sharpening
	 */
	private double extraConsumption;
	
	/**
	 * Sets the ink consumption	
	 */
	public Pencil() {
		consumption = 0.95f;
		extraConsumption = 3;
	}
	
	@Override
	public void write(StringBuilder sb, char sym) {
		if (capacity >= consumption) {
			sb.append(sym);
			
			applyConsumption();
			
			if (symbolsWritten % 20 == 0) {
				capacity -= extraConsumption;
			}
		}		
	}
	
	@Override
	public void erase(StringBuilder sb) {
		sb.deleteCharAt(sb.length() - 1);
	}
}
