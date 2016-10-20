package com.ssa.entity;

import javax.persistence.*;

@Entity
@Table(name="offensive_player")
public class OffensivePlayer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="teamId")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name="position")
	private Position position;
	
	@Column(name="offRanking")
	private Integer offRanking;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Integer getOffRanking() {
		return offRanking;
	}

	public void setOffRanking(Integer offRanking) {
		this.offRanking = offRanking;
	}

	public int getId() {
		return id;
	}
	
	public OffensivePlayer() {}

	public OffensivePlayer(String name, Team team, Position position,
			Integer offRanking) {		
		this.name = name;
		this.team = team;
		this.position = position;
		this.offRanking = offRanking;
	}
	
	
}
