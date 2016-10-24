package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IOffensivePlayerDAO;
import com.ssa.entity.OffensivePlayer;

@Service
public class OffensivePlayerService implements IOffensivePlayerService {

	@Autowired
	private IOffensivePlayerDAO OffensivePlayerDAO;
	
	@Override
	public List<OffensivePlayer> getAllOffensivePlayers() {
		return OffensivePlayerDAO.getAllOffensivePlayers();
	}

	@Override
	public OffensivePlayer getOffensivePlayerById(int id) {
		return OffensivePlayerDAO.getOffensivePlayerById(id);
	}
	
	@Override 
	public OffensivePlayer getOffensivePlayer(OffensivePlayer offensivePlayer) {
		return OffensivePlayerDAO.getOffensivePlayer(offensivePlayer);
	}

	@Override
	public boolean addOffensivePlayer(OffensivePlayer offensivePlayer) {
		return OffensivePlayerDAO.addOffensivePlayer(offensivePlayer);
	}

	@Override
	public void updateOffensivePlayer(OffensivePlayer offensivePlayer) {
		OffensivePlayerDAO.updateOffensivePlayer(offensivePlayer);
	}

	@Override
	public void deleteOffensivePlayer(OffensivePlayer offensivePlayer) {
		OffensivePlayerDAO.deleteOffensivePlayer(offensivePlayer);
	}

}
