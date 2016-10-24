package com.ssa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="position")
public class Position {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="position",fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<OffensivePlayer> playersByPosition = new ArrayList<OffensivePlayer>();
	
	public List<OffensivePlayer> getPlayersByPosition() {
		return this.playersByPosition;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public Position() {}

	public Position(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
}
