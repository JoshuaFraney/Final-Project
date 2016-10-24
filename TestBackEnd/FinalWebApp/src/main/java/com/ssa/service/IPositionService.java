package com.ssa.service;

import java.util.List;

import com.ssa.entity.OffensivePlayer;
import com.ssa.entity.Position;

public interface IPositionService {
	List<Position> getAllPositions();
	Position getPositionById(int id);
	Position getPositionByCode(String code);
	List<OffensivePlayer> getPlayersByPosition(String code);
	boolean addPosition(Position position);
	void updatePosition(Position position);
	void deletePosition(Position position);

}
