package com.ssa.service;

import java.util.List;

import com.ssa.entity.DefensiveStat;

public interface IDefensiveStatService {
	
	List<DefensiveStat> getAllDefensiveStats();
	DefensiveStat getDefensiveStatById(int id);
	boolean addDefensiveStat(DefensiveStat defensiveStat);
	void updateDefensiveStat(DefensiveStat defensiveStat);
	void deleteDefensiveStat(DefensiveStat defensiveStat);
	DefensiveStat getDefensiveStatByAbrev(String abrev);

}
