package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IPositionDAO;
import com.ssa.entity.OffensivePlayer;
import com.ssa.entity.Position;

@Service
public class PositionService implements IPositionService {
	
	@Autowired
	IPositionDAO positionDAO;

	@Override
	public List<Position> getAllPositions() {
		return positionDAO.getAllPositions();
	}

	@Override
	public Position getPositionById(int id) {
		return positionDAO.getPositionById(id);
	}
	
	@Override
	public Position getPositionByCode(String code) {
		return positionDAO.getPositionByCode(code);
	}
	
	@Override
	public List<OffensivePlayer> getPlayersByPosition(String code) {
		return positionDAO.getPlayersByPosition(code);
	}

	@Override
	public boolean addPosition(Position position) {
		return positionDAO.addPosition(position);
	}

	@Override
	public void updatePosition(Position position) {
		positionDAO.updatePosition(position);
	}

	@Override
	public void deletePosition(Position position) {
		positionDAO.deletePosition(position);
	}

}
