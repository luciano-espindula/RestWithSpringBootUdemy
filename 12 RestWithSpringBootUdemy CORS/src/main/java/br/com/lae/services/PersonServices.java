package br.com.lae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lae.converter.DozerConverter;
import br.com.lae.data.model.Person;
import br.com.lae.data.vo.v1.PersonVO;
import br.com.lae.exception.ResourceNotFoundException;
import br.com.lae.repositroy.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;
	
	//LAE - Ver SpringData
	
	public PersonVO create(PersonVO person ) {
		var entity = DozerConverter.parseObject(person,  Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity),  PersonVO.class);
		return vo;
	}
	
	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		var entity = getFindById(id);
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	private Person getFindById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	public PersonVO update(PersonVO person ) {
		var entity = getFindById(person.getKey());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		Person entity = getFindById(id);
		
		repository.delete(entity);
	}
	
}
