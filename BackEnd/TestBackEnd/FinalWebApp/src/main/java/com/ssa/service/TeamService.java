package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.ITeamDAO;
import com.ssa.entity.Team;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private ITeamDAO TeamDAO;
	
	@Override
	public List<Team> getAllTeams() {
		return TeamDAO.getAllTeams();
	}

	@Override
	public Team getTeamById(int id) {
		return TeamDAO.getTeamById(id);
	}
	
	@Override
	public Team getTeamByAbrev(String abrev) {
		return TeamDAO.getTeamByAbrev(abrev);
	}

	@Override
	public boolean addTeam(Team team) {
		return TeamDAO.addTeam(team);
	}

	@Override
	public void updateTeam(Team team) {
		TeamDAO.updateTeam(team);
	}

	@Override
	public void deleteTeam(Team team) {
		TeamDAO.deleteTeam(team);
	}

}
