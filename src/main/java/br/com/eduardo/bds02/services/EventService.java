package br.com.eduardo.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eduardo.bds02.dto.EventDTO;
import br.com.eduardo.bds02.entities.Event;
import br.com.eduardo.bds02.repositories.EventRepository;
import br.com.eduardo.bds02.services.exception.ResourceNotFoundException;

@Service
public class EventService {
	@Autowired
	private EventRepository repo;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = repo.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repo.save(entity);
			return new EventDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found. " + id);
		}
		
	}

	private void copyDtoToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setUrl(dto.getUrl());
		entity.setDate(dto.getDate());
		
	}

}
