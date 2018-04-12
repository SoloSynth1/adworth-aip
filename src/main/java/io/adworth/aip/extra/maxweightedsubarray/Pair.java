package io.adworth.aip.extra.maxweightedsubarray;

public class Pair{
	private int value;
	private int weight;
	private double rating;
	
	public Pair(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	public int getValue() {
		return this.value;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public double getRating() {
		return this.rating;
	}
}
