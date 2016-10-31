package com.ssa.service;

import java.util.List;

import com.ssa.entity.FantasyScoring;
import com.ssa.entity.OffensivePlayer;

public interface IOffensivePlayerService {
	List<OffensivePlayer> getAllOffensivePlayers();
	List<OffensivePlayer> getOffensivePlayersByTeam(String team);
	List<OffensivePlayer> getOffensivePlayersByPosition(String code);
	OffensivePlayer getOffensivePlayerById(int id);
	OffensivePlayer getOffensivePlayerWithStats(Integer id);
	OffensivePlayer getOffensivePlayer(OffensivePlayer offensivePlayer);
	boolean addOffensivePlayer(OffensivePlayer OffensivePlayer);
	void updateOffensivePlayer(OffensivePlayer OffensivePlayer);
	void deleteOffensivePlayer(OffensivePlayer OffensivePlayer);
	List<OffensivePlayer> updateRankings(FantasyScoring fantasyScoring);

}
