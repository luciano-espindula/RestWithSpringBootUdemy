package br.com.lae.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.lae.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	
	public Person create(Person person ) {
		return person;
	}
	
	public Person update(Person person ) {
		return person;
	}
	
	public void delete(String id) {
		
	}
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirtName("Luciano");
		person.setLastName("Espindula");
		person.setAddress("Palhoça - SC - Brasil");
		person.setGender("Male");
		return person;
	}
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirtName("Luciano " + i);
		person.setLastName("Espindula " + i);
		person.setAddress("Palhoça - SC - Brasil " + i);
		person.setGender("Male");
		return person;
	}
	 
}
