package com.ssa.dao;

import java.util.List;

import com.ssa.entity.OffensivePlayer;

public interface IOffensivePlayerDAO {
	List<OffensivePlayer> getAllOffensivePlayers();
	OffensivePlayer getOffensivePlayerById(int id);
	boolean addOffensivePlayer(OffensivePlayer OffensivePlayer);
	void updateOffensivePlayer(OffensivePlayer OffensivePlayer);
	void deleteOffensivePlayer(OffensivePlayer OffensivePlayer);

}
