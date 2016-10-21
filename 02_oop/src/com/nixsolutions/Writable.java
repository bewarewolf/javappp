package com.nixsolutions;

public abstract class Writable implements Comparable<Writable> {

	protected float capacity = 100;
	protected int symbolsWritten = 0;
	protected float consumption;
	
	protected void applyConsumption() {
		this.capacity -= this.consumption;
		this.symbolsWritten++;
	}
	
	public void write(StringBuilder sb, char sym) {}
	
	public void erase(StringBuilder sb) {}
	
	@Override
	public int compareTo(Writable o) {
		
		return ((Float)capacity).compareTo(o.capacity);
	}
	
	@Override
	public String toString() {
		return String.format("%s. Capacity: %s; Symbols written: %s", this.getClass().getSimpleName(), 
				this.capacity, this.symbolsWritten);
	}
}
