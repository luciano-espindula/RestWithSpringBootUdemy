package br.com.lae.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import br.com.lae.data.vo.v1.BookVO;
import br.com.lae.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book", description = "Description for Book", tags = "BookEndPoint")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	BookServices service;
	
	@ApiOperation(value = "New book")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})	
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVO = service.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation("Find all books")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<BookVO> findAll() {
		List<BookVO> books = service.findAll();
		books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		return books;
	}
	
	@ApiOperation("Find by id book")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = service.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}

	@ApiOperation("Update book")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})	
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVO = service.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return bookVO;		
	}
	
	@ApiOperation("Delete book for by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
