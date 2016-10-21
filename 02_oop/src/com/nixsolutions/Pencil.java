package com.nixsolutions;

public class Pencil extends Writable {

	private double extraConsumption;
	
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
