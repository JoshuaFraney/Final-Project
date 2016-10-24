package com.ssa.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="matchup")
public class Matchup {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="week")
	private Integer week;
	
	@ManyToOne
	@JoinColumn(name="homeTeamId")
	private Team homeTeam;
	
	@ManyToOne
	@JoinColumn(name="awayTeamId")
	private Team awayTeam;
	
	@Column(name="final")
	private Boolean isFinal;
	
	@JsonIgnore
	@OneToOne(mappedBy="matchup", fetch=FetchType.EAGER)
	private GameResult gameResult;

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Boolean getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(Boolean isFinal) {
		this.isFinal = isFinal;
	}

	public int getId() {
		return id;
	}
	
	public Matchup() {}

	public Matchup(Integer week, Team homeTeam, Team awayTeam, Boolean isFinal) {
		super();
		this.week = week;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.isFinal = isFinal;
	}
}
