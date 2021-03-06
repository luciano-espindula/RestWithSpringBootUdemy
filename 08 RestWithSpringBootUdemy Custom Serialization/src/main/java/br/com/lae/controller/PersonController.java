package br.com.lae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lae.data.vo.v1.PersonVO;
import br.com.lae.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	//	private PersonService person = new PersonService();
	//	LAE - com o @Autowired faz a injeção de dependência e não precisa criar o objeto
	@Autowired
	private PersonServices services;	
	
	@GetMapping
	public List<PersonVO> findAll() {
		return services.findAll();
	}
	
	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}
	
	@PostMapping
	public PersonVO create(@RequestBody PersonVO person) {
		return services.create(person);
	}

	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) {
		return services.update(person);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}
