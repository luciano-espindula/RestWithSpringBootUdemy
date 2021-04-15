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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lae.data.vo.v1.BookVO;
import br.com.lae.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book EndPoint")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	private BookServices service;
	
	@Autowired
	private PagedResourcesAssembler<BookVO> assembler;
	
	@Operation(summary = "New book")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})	
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVO = service.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@Operation(summary = "Find all books")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "30") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Pageable pageable = getPegeable(page, limit, direction);
		
		Page<BookVO> books = service.findAll(pageable);
		
		books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		return ResponseEntity.ok(assembler.toModel(books));
	}
	
	@Operation(summary = "Find by title books")
	@GetMapping(value = "/findByTitle/{title}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findByTitle(@PathVariable(value = "title") String title,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "30") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Pageable pageable = getPegeable(page, limit, direction);
		
		Page<BookVO> books = service.findByTitle(title, pageable);
		
		books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		return ResponseEntity.ok(assembler.toModel(books));
	}
	
	private Pageable getPegeable(int page, int limit, String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		return PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
	}
	
	@Operation(summary = "Find by id book")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = service.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}

	@Operation(summary = "Update book")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})	
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVO = service.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}	
	
	@Operation(summary = "Delete book for by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
