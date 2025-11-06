package com.example.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.example.contracts.domain.repositories.ActorsRepository;
import com.example.contracts.domain.services.ActorsService;
import com.example.core.domain.exceptions.DuplicateKeyException;
import com.example.core.domain.exceptions.InvalidDataException;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.domain.entities.Actor;

public class ActorsServiceImpl implements ActorsService {
	ActorsRepository dao;
	
	public ActorsServiceImpl(ActorsRepository dao) {
		this.dao = dao;
	}

	@Override
	public Optional<Actor> getOne(Specification<Actor> spec) {
		return Optional.empty();
	}

	@Override
	public List<Actor> getAll(Specification<Actor> spec) {
		return null;
	}

	@Override
	public Page<Actor> getAll(Specification<Actor> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> getAll(Specification<Actor> spec, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Actor> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Actor> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException("No puede ser nulo");
		if(item.isInvalid())
			throw new InvalidDataException("Datos invalidos");
		if(dao.existsById(item.getId()))
			throw new DuplicateKeyException("Ya existe");
		return dao.save(item);
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException("No puede ser nulo");
		if(item.isInvalid())
			throw new InvalidDataException("Datos invalidos");
		if(!dao.existsById(item.getId()))
			throw new NotFoundException();
		return dao.save(item);
	}

	@Override
	public void delete(Actor item) throws InvalidDataException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pasaAJubilacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void repartePremios(String[] premios) {
		// TODO Auto-generated method stub

	}

}
