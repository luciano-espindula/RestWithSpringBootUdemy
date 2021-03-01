package br.com.lae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lae.converter.DozerConverter;
import br.com.lae.data.model.Book;
import br.com.lae.data.vo.v1.BookVO;
import br.com.lae.exception.ResourceNotFoundException;
import br.com.lae.repositroy.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}
	
	public BookVO findById(Long id) {
		var entity = getFindById(id);
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	private Book getFindById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	public BookVO update(BookVO book) {
		var entity = getFindById(book.getKey());
		entity.setId(book.getKey());
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		
		return vo;		
	}
	
	public void delete(Long id) {
		var entity = getFindById(id);
		
		repository.delete(entity);
	}

}
