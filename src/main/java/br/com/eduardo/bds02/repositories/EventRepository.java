package br.com.eduardo.bds02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.bds02.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
