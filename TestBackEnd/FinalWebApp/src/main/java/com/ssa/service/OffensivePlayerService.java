package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IOffensivePlayerDAO;
import com.ssa.dao.ITeamDAO;
import com.ssa.entity.OffensivePlayer;

@Service
public class OffensivePlayerService implements IOffensivePlayerService {

	@Autowired
	private IOffensivePlayerDAO offensivePlayerDAO;
	
	@Autowired
	private ITeamDAO teamDAO;
	
	@Override
	public List<OffensivePlayer> getAllOffensivePlayers() {
		return offensivePlayerDAO.getAllOffensivePlayers();
	}
	
	@Override
	public List<OffensivePlayer> getOffensivePlayersByTeam(String team) {
		return teamDAO.getRoster(team);
	}
	
	@Override
	public List<OffensivePlayer> getOffensivePlayersByPosition(String code) {
		return offensivePlayerDAO.getOffensivePlayersByPosition(code);
	}

	@Override
	public OffensivePlayer getOffensivePlayerById(int id) {
		return offensivePlayerDAO.getOffensivePlayerById(id);
	}
	
	@Override 
	public OffensivePlayer getOffensivePlayer(OffensivePlayer offensivePlayer) {
		return offensivePlayerDAO.getOffensivePlayer(offensivePlayer);
	}

	@Override
	public boolean addOffensivePlayer(OffensivePlayer offensivePlayer) {
		return offensivePlayerDAO.addOffensivePlayer(offensivePlayer);
	}

	@Override
	public void updateOffensivePlayer(OffensivePlayer offensivePlayer) {
		offensivePlayerDAO.updateOffensivePlayer(offensivePlayer);
	}

	@Override
	public void deleteOffensivePlayer(OffensivePlayer offensivePlayer) {
		offensivePlayerDAO.deleteOffensivePlayer(offensivePlayer);
	}

}
