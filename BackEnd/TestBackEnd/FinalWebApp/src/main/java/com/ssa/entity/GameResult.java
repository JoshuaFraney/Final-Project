package com.ssa.entity;

import javax.persistence.*;

@Entity
@Table(name="game_result")
public class GameResult {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="matchupId")
	private Matchup matchup;
	
	@ManyToOne(cascade={CascadeType.ALL,CascadeType.PERSIST})
	@JoinColumn(name="winTeamId")
	private Team winTeam;
	
	@ManyToOne(cascade={CascadeType.ALL,CascadeType.PERSIST})
	@JoinColumn(name="loseTeamId")
	private Team loseTeam;
	
	@Column(name="winScore")
	private Integer winScore;
	
	@Column(name="loseScore")
	private Integer loseScore;
	
	@Column(name="tie")
	private Boolean tie;

	public Matchup getMatchup() {
		return matchup;
	}

	public void setMatchup(Matchup matchup) {
		this.matchup = matchup;
	}

	public Team getWinTeam() {
		return winTeam;
	}

	public void setWinTeam(Team winTeam) {
		this.winTeam = winTeam;
	}

	public Team getLoseTeam() {
		return loseTeam;
	}

	public void setLoseTeam(Team loseTeam) {
		this.loseTeam = loseTeam;
	}

	public Boolean getTie() {
		return tie;
	}

	public void setTie(Boolean tie) {
		this.tie = tie;
	}

	public int getId() {
		return id;
	}
	
	public Integer getWinScore() {
		return winScore;
	}

	public void setWinScore(Integer winScore) {
		this.winScore = winScore;
	}

	public Integer getLoseScore() {
		return loseScore;
	}

	public void setLoseScore(Integer loseScore) {
		this.loseScore = loseScore;
	}

	public GameResult() {}

	public GameResult(Matchup matchup, Team winTeam, Team loseTeam, Boolean tie, Integer loseScore, Integer winScore) {
		this.matchup = matchup;
		this.winTeam = winTeam;
		this.loseTeam = loseTeam;
		this.tie = tie;
		this.loseScore = loseScore;
		this.winScore = winScore;
	}
}
