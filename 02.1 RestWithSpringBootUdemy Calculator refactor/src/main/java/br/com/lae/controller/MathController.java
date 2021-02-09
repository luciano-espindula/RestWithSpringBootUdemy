package br.com.lae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lae.math.SimpleMath;
import br.com.lae.request.converters.NumberConverter;
import br.com.lae.request.validate.ValidateImput;

@RestController
public class MathController {
	
//	private SimpleMath math = new SimpleMath();
	@Autowired
	private SimpleMath math;
	
	@Autowired
	private NumberConverter converter;
	
	@Autowired
	private ValidateImput validate;
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		validate.isTwoStrNumberValid(numberOne, numberTwo);
		return math.sum(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
	}

	@RequestMapping(value="/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		validate.isTwoStrNumberValid(numberOne, numberTwo);
		return math.sub(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mult(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		validate.isTwoStrNumberValid(numberOne, numberTwo);
		return math.mult(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
	}

	@RequestMapping(value="/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		validate.isTwoStrNumberValid(numberOne, numberTwo);
		return math.div(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
	}

	@RequestMapping(value="/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		validate.isTwoStrNumberValid(numberOne, numberTwo);
		return math.mean(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/sqrt/{numberOne}", method = RequestMethod.GET)
	public Double sqrt(@PathVariable("numberOne") String numberOne) throws Exception {
		validate.isTwoStrNumberValid(numberOne, numberOne);
		return math.sqrt(converter.convertToDouble(numberOne));
	}
	

}
