package com.ssa.dao;

import java.util.List;

import com.ssa.entity.FantasyScoring;
import com.ssa.entity.OffensivePlayer;

public interface IOffensivePlayerDAO {
	List<OffensivePlayer> getAllOffensivePlayers();
	List<OffensivePlayer> getOffensivePlayersByPosition(String code);
	OffensivePlayer getOffensivePlayerById(int id);
	OffensivePlayer getOffensivePlayerWithStats(Integer id);
	OffensivePlayer getOffensivePlayer(OffensivePlayer offensivePlayer);
	boolean addOffensivePlayer(OffensivePlayer OffensivePlayer);
	void updateOffensivePlayer(OffensivePlayer OffensivePlayer);
	void deleteOffensivePlayer(OffensivePlayer OffensivePlayer);
	List<OffensivePlayer> updateRankings(FantasyScoring fantasyScoring);
}
