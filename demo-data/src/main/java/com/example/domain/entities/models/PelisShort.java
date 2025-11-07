package com.example.domain.entities.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.domain.entities.Film;

@Projection(name = "titulos", types = { Film.class} )
public interface PelisShort {
	Integer getId();
	@Value("#{target.title}")
	String getTitulo();
}
