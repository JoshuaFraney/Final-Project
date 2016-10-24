package com.ssa.entity;

import javax.persistence.*;

@Entity
@Table(name="defensive_stat")
public class DefensiveStat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="passYardsAllowed")
	private Integer passYardsAllowed;
	
	@Column(name="rushYardsAllowed")
	private Integer rushYardsAllowed;
	
	@ManyToOne
	@JoinColumn(name="teamId")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name="oppTeamId")
	private Team opponent;
	
	@Column(name="pointsAllowed")
	private Integer pointsAllowed;
	
	@Column(name="sacks")
	private Integer sacks;
	
	@Column(name="interceptions")
	private Integer interceptions;
	
	@Column(name="fumbleRcvry")
	private Integer fumbleRcvry;
	
	@Column(name="defRanking")
	private Integer defRanking;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getSacks() {
		return sacks;
	}

	public void setSacks(Integer sacks) {
		this.sacks = sacks;
	}

	public Integer getInterceptions() {
		return interceptions;
	}

	public void setInterceptions(Integer interceptions) {
		this.interceptions = interceptions;
	}

	public Integer getFumbleRcvry() {
		return fumbleRcvry;
	}

	public void setFumbleRcvry(Integer fumbleRcvry) {
		this.fumbleRcvry = fumbleRcvry;
	}

	public int getId() {
		return id;
	}
	
	public Integer getDefRanking() {
		return defRanking;
	}

	public void setDefRanking(Integer defRanking) {
		this.defRanking = defRanking;
	}

	public Integer getPassYardsAllowed() {
		return passYardsAllowed;
	}

	public void setPassYardsAllowed(Integer passYardsAllowed) {
		this.passYardsAllowed = passYardsAllowed;
	}

	public Integer getRushYardsAllowed() {
		return rushYardsAllowed;
	}

	public void setRushYardsAllowed(Integer rushYardsAllowed) {
		this.rushYardsAllowed = rushYardsAllowed;
	}

	public Integer getPointsAllowed() {
		return pointsAllowed;
	}

	public void setPointsAllowed(Integer pointsAllowed) {
		this.pointsAllowed = pointsAllowed;
	}

	public DefensiveStat() {}

	public DefensiveStat(Integer passYardsAllowed, Integer rushYardsAllowed, Team team, Integer pointsAllowed,
			Integer sacks, Integer interceptions, Integer fumbleRcvry, Integer defRanking) {
		super();
		this.passYardsAllowed = passYardsAllowed;
		this.rushYardsAllowed = rushYardsAllowed;
		this.team = team;
		this.pointsAllowed = pointsAllowed;
		this.sacks = sacks;
		this.interceptions = interceptions;
		this.fumbleRcvry = fumbleRcvry;
		this.defRanking = defRanking;
	}


}
