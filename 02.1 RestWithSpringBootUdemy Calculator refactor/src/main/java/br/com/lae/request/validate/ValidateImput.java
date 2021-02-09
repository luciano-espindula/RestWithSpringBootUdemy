package br.com.lae.request.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lae.exception.UnsupportedMathOperationException;
import br.com.lae.request.converters.NumberConverter;
@Service
public class ValidateImput {

	@Autowired
	NumberConverter converter;
	
	public void isTwoStrNumberValid(String numberOne, String numberTwo) {
		if (!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
	}
}
