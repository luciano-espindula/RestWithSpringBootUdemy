package br.com.lae.math;

public class SimpleMath {
	
	public Double sum(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}
	
	public Double sub(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}

	public Double mult(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	public Double div(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	public Double mean(Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo) / 2.0;
	}
	
	public Double sqrt(Double numberOne) {
		return Math.sqrt(numberOne);
	}
	 
	
}
