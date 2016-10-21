package com.nixsolutions;


/**
 * Represents base class for writable objects.
 * <p>
 * Implements interface {@link Comparable<T>} to provide the ability
 * of sorting the collection of Writable instances by the ink capacity
 * 
 * @author plakhotnichenko
 */
public abstract class Writable implements Comparable<Writable> {

	/**
	 * Amount of ink	
	 */
	protected float capacity = 100;
	
	/**
	 * Count of symbols written by this instance	
	 */
	protected int symbolsWritten = 0;
	
	/**
	 * Consumption of ink per 1 symbol	
	 */
	protected float consumption;
	
	/**
	 * Calculates consumption of ink	
	 */
	protected void applyConsumption() {
		this.capacity -= this.consumption;
		this.symbolsWritten++;
	}
	
	/**
	 * Writes single character into StringBuilder instance	
	 *
	 * @param  sb  instance of {@link StringBuilder} to update 
	 * @param  sym symbol to be written 
	 */
	public void write(StringBuilder sb, char sym) {}
	
	/**
	 * Erases last character from StringBuilder instance	
	 *
	 * @param  sb  instance of {@link StringBuilder} to update 
	 */
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
