package br.com.lae.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lae.math.SimpleMath;
import br.com.lae.math.ValidateNumber;
import br.com.lae.request.converters.NumberConverter;

@RestController
public class MathController {
	
	private SimpleMath math = new SimpleMath();
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		ValidateNumber.isTwoStrNumberValid(numberOne, numberTwo);
		return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value="/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		ValidateNumber.isTwoStrNumberValid(numberOne, numberTwo);
		return math.sub(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mult(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		ValidateNumber.isTwoStrNumberValid(numberOne, numberTwo);
		return math.mult(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value="/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		ValidateNumber.isTwoStrNumberValid(numberOne, numberTwo);
		return math.div(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value="/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		ValidateNumber.isTwoStrNumberValid(numberOne, numberTwo);
		return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/sqrt/{numberOne}", method = RequestMethod.GET)
	public Double sqrt(@PathVariable("numberOne") String numberOne) throws Exception {
		ValidateNumber.isTwoStrNumberValid(numberOne, numberOne);
		return math.sqrt(NumberConverter.convertToDouble(numberOne));
	}
	

}
