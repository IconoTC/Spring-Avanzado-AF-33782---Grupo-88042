package com.example.domain.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.core.domain.entities.AbstractEntity;
import com.example.core.domain.validation.Uppercase;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor extends AbstractEntity<Actor> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id", unique=true, nullable=false)
	private int id;

	@Column(name="first_name", nullable=false, length=45)
	@NotBlank
	@Size(max = 45, min = 2)
	@Uppercase
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	@NotBlank
	@Size(max = 45, min = 2)
	@Pattern(regexp = "^[A-Z]+$", message = "debe estar en mayusculas")
	private String lastName;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	@PastOrPresent
	private LocalDateTime lastUpdate;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="actor", fetch = FetchType.LAZY)
	private List<FilmActor> filmActors;

	public Actor() {
		filmActors = new ArrayList<FilmActor>();
	}

	public Actor(int id) {
		this();
		this.id = id;
	}

	public Actor(int id, String firstName, String lastName) {
		this(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor(String firstName, String lastName) {
		this(0, firstName, lastName);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setActor(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setActor(null);

		return filmActor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Actor))
			return false;
		return id == ((Actor) obj).id;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate=" + lastUpdate
				+ "]";
	}

	public void jubilate() {
		// ...
	}
	
	public void recibePremio(String premio) {
		// ...
	}
}