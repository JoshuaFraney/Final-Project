package com.ssa.dao;

import java.util.List;

import com.ssa.entity.FantasyScoring;

public interface IFantasyScoringDAO {

	List<FantasyScoring> getAllFantasyScoring();
	FantasyScoring getFantasyScoringById(Integer id);
	Boolean addFantasyScoring(FantasyScoring fantasyScoring);
}
