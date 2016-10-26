package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IFantasyScoringDAO;
import com.ssa.entity.FantasyScoring;

@Service
public class FantasyScoringService implements IFantasyScoringService {

	@Autowired
	IFantasyScoringDAO fantasyScoringDAO;
	
	@Override
	public List<FantasyScoring> getAllFantasyScoring() {
		return fantasyScoringDAO.getAllFantasyScoring();
	}

	@Override
	public FantasyScoring getFantasyScoringById(Integer id) {
		return fantasyScoringDAO.getFantasyScoringById(id);
	}

	@Override
	public Boolean addFantasyScoring(FantasyScoring fantasyScoring) {
		return fantasyScoringDAO.addFantasyScoring(fantasyScoring);
	}

}
