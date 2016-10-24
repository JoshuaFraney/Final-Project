package com.ssa.service;

import java.util.List;

import com.ssa.entity.OffensiveStat;

public interface IOffensiveStatService {

	List<OffensiveStat> getAllOffensiveStats();
	OffensiveStat getOffensiveStatById(int id);
	boolean addOffensiveStat(OffensiveStat OffensiveStat);
	void updateOffensiveStat(OffensiveStat OffensiveStat);
	void deleteOffensiveStat(OffensiveStat OffensiveStat);
}
