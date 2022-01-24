package br.com.eduardo.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.eduardo.bds02.dto.CityDTO;
import br.com.eduardo.bds02.entities.City;
import br.com.eduardo.bds02.repositories.CityRepository;
import br.com.eduardo.bds02.services.exception.DatabaseException;
import br.com.eduardo.bds02.services.exception.ResourceNotFoundException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repo;

	public List<CityDTO> findAll() {
		List<City> list = repo.findAll(Sort.by("name"));
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}

	public CityDTO insert(CityDTO cityDTO) {
		City entity = new City();
		entity.setName(cityDTO.getName());
		entity = repo.save(entity);
		return new CityDTO(entity);
	}

	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found. " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation - resource could not be removed, because it don't exists in database");
		}
	}


}
