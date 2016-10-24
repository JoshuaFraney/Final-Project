package com.ssa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@OneToMany(mappedBy="player",fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<OffensiveStat> gameStats = new ArrayList<OffensiveStat>();

	public List<OffensiveStat> getGameStats() {
		return gameStats;
	}

	public void setGameStats(List<OffensiveStat> gameStats) {
		this.gameStats = gameStats;
	}

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
