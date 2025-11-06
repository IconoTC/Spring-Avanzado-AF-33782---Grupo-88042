package com.example.contracts.domain.services;

import com.example.core.contracts.domain.services.SpecificationDomainService;
import com.example.domain.entities.Actor;

public interface ActorsService extends SpecificationDomainService<Actor, Integer> {
	void pasaAJubilacion();
	void repartePremios(String[] premios);
}
