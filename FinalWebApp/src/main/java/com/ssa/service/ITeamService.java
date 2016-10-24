package com.ssa.service;

import java.util.List;

import com.ssa.entity.Team;

public interface ITeamService {
	
	List<Team> getAllTeams();
	Team getTeamById(int id);
	Team getTeamByAbrev(String abrev);
	boolean addTeam(Team Team);
	void updateTeam(Team Team);
	void deleteTeam(Team Team);
}
