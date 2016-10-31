package com.ssa.entity;

import javax.persistence.*;

@Entity
@Table(name="offensive_stat")
public class OffensiveStat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="playerId")
	private OffensivePlayer player;
	
	@ManyToOne
	@JoinColumn(name="oppTeamId")
	private Team opponent;
	
	@Column(name="passAttempts")
	private Integer passAttempts;
	
	@Column(name="passCompletions")
	private Integer passCompletions;
	
	@Column(name="passYards")
	private Integer passYards;
	
	@Column(name="passTouchdowns")
	private Integer passTouchdowns;
	
	@Column(name="passInterceptions")
	private Integer passInterceptions;
	
	@Column(name="receptions")
	private Integer receptions;
	
	@Column(name="recYards")
	private Integer recYards;
	
	@Column(name="recTouchdowns")
	private Integer recTouchdowns;
	
	@Column(name="rushAttempts")
	private Integer rushAttempts;
	
	@Column(name="rushYards")
	private Integer rushYards;
	
	@Column(name="rushTouchdowns")
	private Integer rushTouchdowns;
	
	@Column(name="fumbles")
	private Integer fumbles;

	public void addStats(OffensiveStat stat) {
		this.passAttempts += stat.passAttempts;
		this.passCompletions += stat.passCompletions;
		this.passYards += stat.passYards;
		this.passTouchdowns += stat.passTouchdowns;
		this.passInterceptions += stat.passInterceptions;
		this.receptions += stat.receptions;
		this.recYards += stat.recYards;
		this.recTouchdowns += stat.recTouchdowns;
		this.rushAttempts += stat.rushAttempts;
		this.rushTouchdowns += stat.rushTouchdowns;
		this.rushYards += stat.rushYards;
		this.fumbles += stat.fumbles;
	}
	
	public Team getOpponent() {
		return opponent;
	}

	public void setOpponent(Team opponent) {
		this.opponent = opponent;
	}

	public OffensivePlayer getPlayer() {
		return player;
	}

	public void setPlayer(OffensivePlayer player) {
		this.player = player;
	}

	public Integer getPassAttempts() {
		return passAttempts;
	}

	public void setPassAttempts(Integer passAttempts) {
		this.passAttempts = passAttempts;
	}

	public Integer getPassCompletions() {
		return passCompletions;
	}

	public void setPassCompletions(Integer passCompletions) {
		this.passCompletions = passCompletions;
	}

	public Integer getPassYards() {
		return passYards;
	}

	public void setPassYards(Integer passYards) {
		this.passYards = passYards;
	}

	public Integer getPassTouchdowns() {
		return passTouchdowns;
	}

	public void setPassTouchdowns(Integer passTouchdowns) {
		this.passTouchdowns = passTouchdowns;
	}

	public Integer getReceptions() {
		return receptions;
	}

	public void setReceptions(Integer receptions) {
		this.receptions = receptions;
	}

	public Integer getRecYards() {
		return recYards;
	}

	public void setRecYards(Integer recYards) {
		this.recYards = recYards;
	}

	public Integer getRushAttempts() {
		return rushAttempts;
	}

	public void setRushAttempts(Integer rushAttempts) {
		this.rushAttempts = rushAttempts;
	}

	public Integer getRushYards() {
		return rushYards;
	}

	public void setRushYards(Integer rushYards) {
		this.rushYards = rushYards;
	}

	public Integer getPassInterceptions() {
		return passInterceptions;
	}

	public void setPassInterceptions(Integer passInterceptions) {
		this.passInterceptions = passInterceptions;
	}

	public Integer getRecTouchdowns() {
		return recTouchdowns;
	}

	public void setRecTouchdowns(Integer recTouchdowns) {
		this.recTouchdowns = recTouchdowns;
	}

	public Integer getRushTouchdowns() {
		return rushTouchdowns;
	}

	public void setRushTouchdowns(Integer rushTouchdowns) {
		this.rushTouchdowns = rushTouchdowns;
	}

	public Integer getFumbles() {
		return fumbles;
	}

	public void setFumbles(Integer fumbles) {
		this.fumbles = fumbles;
	}

	public int getId() {
		return id;
	}

	public OffensiveStat() {}

	public OffensiveStat(OffensivePlayer player, Integer passAttempts, Integer passCompletions, Integer passYards,
			Integer passTouchdowns, Integer passInterceptions, Integer receptions, Integer recYards,
			Integer recTouchdowns, Integer rushAttempts, Integer rushYards, Integer rushTouchdowns, Integer fumbles) {
		this.player = player;
		this.passAttempts = passAttempts;
		this.passCompletions = passCompletions;
		this.passYards = passYards;
		this.passTouchdowns = passTouchdowns;
		this.passInterceptions = passInterceptions;
		this.receptions = receptions;
		this.recYards = recYards;
		this.recTouchdowns = recTouchdowns;
		this.rushAttempts = rushAttempts;
		this.rushYards = rushYards;
		this.rushTouchdowns = rushTouchdowns;
		this.fumbles = fumbles;
	}


}
