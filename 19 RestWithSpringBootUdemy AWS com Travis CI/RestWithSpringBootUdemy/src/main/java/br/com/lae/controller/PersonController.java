package br.com.lae.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lae.data.vo.v1.PersonVO;
import br.com.lae.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin
@Tag(name = "Person EndPoint") 
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	//	LAE - com o @Autowired faz a injeção de dependência e não precisa criar o objeto
	@Autowired
	private PersonServices services;	
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;

	@Operation(summary = "Find all persons")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Pageable pageable = getPegeable(page, limit, direction);
		
		Page<PersonVO> persons = services.findAll(pageable);
		
		persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
//		PagedModel<EntityModel<PersonVO>> pagedModel = assembler.toModel(persons);
		
		return ResponseEntity.ok(assembler.toModel(persons));
		
		//LAE - Assim como o Leandro fez ele mata o resumo
//		Link findAllLink = linkTo(methodOn(PersonController.class).findAll(page, limit, direction)).withSelfRel();
//		return ResponseEntity.ok(CollectionModel.of(persons, findAllLink));
	}

	private Pageable getPegeable(int page, int limit, String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		return PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
	}
	
	@Operation(summary = "Find by firstName persons")
	@GetMapping(value = "/findByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<CollectionModel<?>> findByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Pageable pageable = getPegeable(page, limit, direction);
		
		Page<PersonVO> persons = services.findByName(firstName, pageable);
		
		persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		//LAE - Não precisa fazer o linkTo quando usa o PagedResourcesAssembler
//		Link findAllLink = linkTo(methodOn(PersonController.class).findByName(firstName, page, limit, direction)).withSelfRel();

		return ResponseEntity.ok(assembler.toModel(persons));
	}
	
//	@CrossOrigin(origins = {"http://localhost:8080"})
	@Operation(summary = "Find by id person")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
//	@CrossOrigin(origins = {"http://localhost:8080", "http://edudio.com.br"})
	@Operation(summary = "New person")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = services.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@Operation(summary = "Update person")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = services.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@Operation(summary = "Disable a person by id")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = services.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@Operation(summary = "Delete person by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}
