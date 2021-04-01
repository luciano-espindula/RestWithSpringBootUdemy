package br.com.luc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luc.converter.DozerConverter;
import br.com.luc.data.model.Book;
import br.com.luc.data.vo.v1.BookVO;
import br.com.luc.exception.ResourceNotFoundException;
import br.com.luc.repository.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public Page<BookVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToBookVO);
	}
	
	public Page<BookVO> findByTitle(String title, Pageable pageable) {
		var page = repository.findByTitle(title, pageable);
		return page.map(this::convertToBookVO);
	}
	
	private BookVO convertToBookVO(Book entity) {
		return DozerConverter.parseObject(entity, BookVO.class);
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
		//entity.setId(book.getKey());
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		
		return vo;		
	}
	
	public void delete(Long id) {
		Book entity = getFindById(id);
		
		repository.delete(entity);
	}

}
