package com.ssa.dao;

import java.util.List;

import com.ssa.entity.Matchup;

public interface IMatchupDAO {

	List<Matchup> getAllMatchups();
	Matchup getMatchupById(int id);
	List<Matchup> getMatchupsByWeek(Integer week);
	boolean addMatchup(Matchup Matchup);
	void updateMatchup(Matchup Matchup);
	void deleteMatchup(Matchup Matchup);
}
