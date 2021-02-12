package br.com.lae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lae.exception.ResourceNotFoundException;
import br.com.lae.model.Person;
import br.com.lae.repositroy.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;
	
	//LAE - Ver SpringData
	
	public Person create(Person person ) {
		return repository.save(person);
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}
	
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	public Person update(Person person ) {
		Person entity = findById(person.getId());
		entity.setFirtName(person.getFirtName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		Person entity = findById(id);
		
		repository.delete(entity);
	}
	
}
