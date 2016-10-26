package com.ssa.service;

import java.util.List;

import com.ssa.entity.FantasyScoring;

public interface IFantasyScoringService {
	
	List<FantasyScoring> getAllFantasyScoring();
	FantasyScoring getFantasyScoringById(Integer id);
	Boolean addFantasyScoring(FantasyScoring fantasyScoring);
}
