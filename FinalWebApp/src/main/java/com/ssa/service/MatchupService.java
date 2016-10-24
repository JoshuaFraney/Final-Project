package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IMatchupDAO;
import com.ssa.entity.Matchup;

@Service
public class MatchupService implements IMatchupService {

	@Autowired
	private IMatchupDAO MatchupDAO;
	
	@Override
	public List<Matchup> getAllMatchups() {
		return MatchupDAO.getAllMatchups();
	}

	@Override
	public Matchup getMatchupById(int id) {
		return MatchupDAO.getMatchupById(id);
	}
	
	@Override
	public List<Matchup> getMatchupsByWeek(Integer week) {
		return MatchupDAO.getMatchupsByWeek(week);
	}

	@Override
	public boolean addMatchup(Matchup matchup) {
		return MatchupDAO.addMatchup(matchup);
	}

	@Override
	public void updateMatchup(Matchup matchup) {
		MatchupDAO.updateMatchup(matchup);
	}

	@Override
	public void deleteMatchup(Matchup matchup) {
		MatchupDAO.deleteMatchup(matchup);
	}

}
