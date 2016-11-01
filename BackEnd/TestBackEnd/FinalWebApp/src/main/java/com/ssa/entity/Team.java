package com.ssa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="team")
public class Team {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="division")
	private String division;
	
	@Column(name="teamName")
	private String teamName;
	
	@Column(name="abrev")
	private String abrev;
	
	@Column(name="wins")	
	private Integer wins;
	
	@Column(name="losses")
	private Integer losses;
	
	@Column(name="ties")
	private Integer ties;
	
	@Column(name="ovrRank")
	private Integer ovrRank;
	
	@JsonIgnore
	@OneToMany(mappedBy="team",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<OffensivePlayer> roster = new ArrayList<OffensivePlayer>();
	
	@JsonIgnore
	@OneToMany(mappedBy="homeTeam",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Matchup> homeGames = new ArrayList<Matchup>();
	
	@JsonIgnore
	@OneToMany(mappedBy="awayTeam",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Matchup> awayGames = new ArrayList<Matchup>();
	
	@JsonIgnore
	@OneToMany(mappedBy="winTeam",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<GameResult> winResults = new ArrayList<GameResult>();
	
	@JsonIgnore
	@OneToMany(mappedBy="loseTeam",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<GameResult> loseResults = new ArrayList<GameResult>();
	
	@JsonIgnore
	@OneToOne(mappedBy="team",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private DefensiveStat defensiveStat;
	
	public void addWin() {this.wins++;}
	public void addLoss() {this.losses++;}
	public void addTie() {this.ties++;}
	
	public DefensiveStat getDefensiveStat() {
		return defensiveStat;
	}
	public void setDefensiveStat(DefensiveStat defensiveStat) {
		this.defensiveStat = defensiveStat;
	}
	public void setHomeGames(List<Matchup> homeGames) {
		this.homeGames = homeGames;
	}
	public void setAwayGames(List<Matchup> awayGames) {
		this.awayGames = awayGames;
	}
	public void setWinResults(List<GameResult> winResults) {
		this.winResults = winResults;
	}
	public void setLoseResults(List<GameResult> loseResults) {
		this.loseResults = loseResults;
	}
	public List<GameResult> getWinResults() {
		return winResults;
	}
	
	public List<GameResult> getLoseResults() {
		return loseResults;
	}
	
	public List<Matchup> getHomeGames() {
		return homeGames;
	}
	
	public List<Matchup> getAwayGames() {
		return awayGames;
	}
	
	public String getAbrev() {
		return abrev;
	}
	
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins==null?0:wins;
	}

	public Integer getLosses() {
		return losses;
	}

	public void setLosses(Integer losses) {
		this.losses = losses==null?0:losses;
	}

	public Integer getTies() {
		return ties;
	}

	public void setTies(Integer ties) {
		this.ties = ties==null?0:ties;
	}

	public Integer getOvrRank() {
		return ovrRank;
	}

	public void setOvrRank(Integer ovrRank) {
		this.ovrRank = ovrRank;
	}

	public int getId() {
		return id;
	}
	
	public Team() {
		this.wins = 0;
		this.losses = 0;
		this.ties = 0;
	}

	public Team(String division, String teamName, String abrev, Integer wins, Integer losses,
			Integer ties, Integer ovrRank) {
		this.division = division;
		this.teamName = teamName;
		this.abrev = abrev;
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
		this.ovrRank = ovrRank;
	}
	
	
}