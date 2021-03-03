package br.com.lae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lae.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
