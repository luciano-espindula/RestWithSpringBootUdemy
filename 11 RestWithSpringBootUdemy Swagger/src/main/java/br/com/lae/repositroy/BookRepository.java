package br.com.lae.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lae.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
