package com.ssa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fantasy_scoring")
public class FantasyScoring {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="reception")
	private Double reception;
	
	@Column(name="rush_yard")
	private Double rushYard;
	
	@Column(name="rec_yard")
	private Double recYard;
	
	@Column(name="pass_yard")
	private Double passYard;
	
	@Column(name="pass_touchdown")
	private Integer passTouchdown;
	
	@Column(name="rush_touchdown")
	private Integer rushTouchdown;
	
	@Column(name="rec_touchdown")
	private Integer recTouchdown;
	
	@Column(name="interception")
	private Integer interception;
	
	@Column(name="fumble_lost")
	private Integer fumbleLost;
	
	@Column(name="def_0_points")
	private Integer def0Points;
	
	@Column(name="def_6_points")
	private Integer def6Points;
	
	@Column(name="def_13_points")
	private Integer def13Points;
	
	@Column(name="def_17_points")
	private Integer def17Points;
	
	@Column(name="def_27_points")
	private Integer def27Points;
	
	@Column(name="def_34_points")
	private Integer def34Points;
	
	@Column(name="def_45_points")
	private Integer def45Points;
	
	@Column(name="def_over_45_points")
	private Integer defOver45Points;
	
	@Column(name="def_fumble_rec")
	private Integer defFumbleRec;
	
	@Column(name="def_interception")
	private Integer defInterception;
	
	@Column(name="def_sack")
	private Integer defSack;

	public Double getOffensiveScore(OffensiveStat stats) {
		return (stats.getPassYards() * this.getPassYard()) + 
				(stats.getPassTouchdowns() * this.getPassTouchdown()) +
				(stats.getPassInterceptions() * this.getInterception()) + 
				(stats.getReceptions() * this.getReception()) +
				(stats.getRecYards() * this.getRecYard()) +
				(stats.getRecTouchdowns() * this.getRecTouchdown()) +
				(stats.getRushYards() * this.getRushYard()) +
				(stats.getRushTouchdowns() * this.getRushTouchdown()) +
				(stats.getFumbles() * this.getFumbleLost());
	}
	
	public Double getDefensiveScore(DefensiveStat stats) {
		Double score = 0.0;
		if(stats.getPointsAllowed()==0) {score += this.getDef0Points();}
		else if(stats.getPointsAllowed() <= 6) {score += this.getDef6Points();}
		else if(stats.getPointsAllowed() <= 13) {score += this.getDef13Points();}
		else if(stats.getPointsAllowed() <= 17) {score += this.getDef17Points();}
		else if(stats.getPointsAllowed() <= 27) {score += this.getDef27Points();}
		else if(stats.getPointsAllowed() <= 34) {score += this.getDef34Points();}
		else if(stats.getPointsAllowed() <= 45) {score += this.getDef45Points();}
		else {score += this.getDefOver45Points();}
		score += stats.getFumbleRcvry() * this.getDefFumbleRec();
		score += stats.getInterceptions() * this.getDefInterception();
		score += stats.getSacks() * this.getDefSack();
		return score;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getReception() {
		return reception;
	}

	public void setReception(Double reception) {
		this.reception = reception;
	}

	public Double getRushYard() {
		return rushYard;
	}

	public void setRushYard(Double rushYard) {
		this.rushYard = rushYard;
	}

	public Double getRecYard() {
		return recYard;
	}

	public void setRecYard(Double recYard) {
		this.recYard = recYard;
	}

	public Double getPassYard() {
		return passYard;
	}

	public void setPassYard(Double passYard) {
		this.passYard = passYard;
	}

	public Integer getPassTouchdown() {
		return passTouchdown;
	}

	public void setPassTouchdown(Integer passTouchdown) {
		this.passTouchdown = passTouchdown;
	}

	public Integer getRushTouchdown() {
		return rushTouchdown;
	}

	public void setRushTouchdown(Integer rushTouchdown) {
		this.rushTouchdown = rushTouchdown;
	}

	public Integer getRecTouchdown() {
		return recTouchdown;
	}

	public void setRecTouchdown(Integer recTouchdown) {
		this.recTouchdown = recTouchdown;
	}

	public Integer getInterception() {
		return interception;
	}

	public void setInterception(Integer interception) {
		this.interception = interception;
	}

	public Integer getFumbleLost() {
		return fumbleLost;
	}

	public void setFumbleLost(Integer fumbleLost) {
		this.fumbleLost = fumbleLost;
	}

	public Integer getDef0Points() {
		return def0Points;
	}

	public void setDef0Points(Integer def0Points) {
		this.def0Points = def0Points;
	}

	public Integer getDef6Points() {
		return def6Points;
	}

	public void setDef6Points(Integer def6Points) {
		this.def6Points = def6Points;
	}

	public Integer getDef13Points() {
		return def13Points;
	}

	public void setDef13Points(Integer def13Points) {
		this.def13Points = def13Points;
	}

	public Integer getDef17Points() {
		return def17Points;
	}

	public void setDef17Points(Integer def17Points) {
		this.def17Points = def17Points;
	}

	public Integer getDef27Points() {
		return def27Points;
	}

	public void setDef27Points(Integer def27Points) {
		this.def27Points = def27Points;
	}

	public Integer getDef34Points() {
		return def34Points;
	}

	public void setDef34Points(Integer def34Points) {
		this.def34Points = def34Points;
	}

	public Integer getDef45Points() {
		return def45Points;
	}

	public void setDef45Points(Integer def45Points) {
		this.def45Points = def45Points;
	}

	public Integer getDefOver45Points() {
		return defOver45Points;
	}

	public void setDefOver45Points(Integer defOver45Points) {
		this.defOver45Points = defOver45Points;
	}

	public Integer getDefFumbleRec() {
		return defFumbleRec;
	}

	public void setDefFumbleRec(Integer defFumbleRec) {
		this.defFumbleRec = defFumbleRec;
	}

	public Integer getDefInterception() {
		return defInterception;
	}

	public void setDefInterception(Integer defInterception) {
		this.defInterception = defInterception;
	}

	public Integer getDefSack() {
		return defSack;
	}

	public void setDefSack(Integer defSack) {
		this.defSack = defSack;
	}

	public int getId() {
		return id;
	}

	public FantasyScoring() {
		
	}

	public FantasyScoring(String description, Double reception, Double rushYard, Double recYard, Double passYard,
			Integer passTouchdown, Integer rushTouchdown, Integer recTouchdown, Integer interception,
			Integer fumbleLost, Integer def0Points, Integer def6Points, Integer def13Points, Integer def17Points,
			Integer def27Points, Integer def34Points, Integer def45Points, Integer defOver45Points,
			Integer defFumbleRec, Integer defInterception, Integer defSack) {
		this.description = description;
		this.reception = reception;
		this.rushYard = rushYard;
		this.recYard = recYard;
		this.passYard = passYard;
		this.passTouchdown = passTouchdown;
		this.rushTouchdown = rushTouchdown;
		this.recTouchdown = recTouchdown;
		this.interception = interception;
		this.fumbleLost = fumbleLost;
		this.def0Points = def0Points;
		this.def6Points = def6Points;
		this.def13Points = def13Points;
		this.def17Points = def17Points;
		this.def27Points = def27Points;
		this.def34Points = def34Points;
		this.def45Points = def45Points;
		this.defOver45Points = defOver45Points;
		this.defFumbleRec = defFumbleRec;
		this.defInterception = defInterception;
		this.defSack = defSack;
	}
	
}
