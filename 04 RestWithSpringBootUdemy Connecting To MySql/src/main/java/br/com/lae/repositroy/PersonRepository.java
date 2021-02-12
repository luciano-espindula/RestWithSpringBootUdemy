package br.com.lae.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lae.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
