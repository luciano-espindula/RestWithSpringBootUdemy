package br.com.lae.math;

import br.com.lae.exception.UnsupportedMathOperationException;
import br.com.lae.request.converters.NumberConverter;

public class ValidateNumber {

	public static void isTwoStrNumberValid(String numberOne, String numberTwo) {
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
	}
}
