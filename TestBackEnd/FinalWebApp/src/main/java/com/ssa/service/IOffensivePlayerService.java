package com.ssa.service;

import java.util.List;

import com.ssa.entity.OffensivePlayer;

public interface IOffensivePlayerService {
	List<OffensivePlayer> getAllOffensivePlayers();
	OffensivePlayer getOffensivePlayerById(int id);
	OffensivePlayer getOffensivePlayer(OffensivePlayer offensivePlayer);
	boolean addOffensivePlayer(OffensivePlayer OffensivePlayer);
	void updateOffensivePlayer(OffensivePlayer OffensivePlayer);
	void deleteOffensivePlayer(OffensivePlayer OffensivePlayer);

}
