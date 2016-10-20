package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IDefensiveStatDAO;
import com.ssa.entity.DefensiveStat;

@Service
public class DefensiveStatService implements IDefensiveStatService {

	@Autowired
	private IDefensiveStatDAO DefensiveStatDAO;
	
	@Override
	public List<DefensiveStat> getAllDefensiveStats() {
		return DefensiveStatDAO.getAllDefensiveStats();
	}

	@Override
	public DefensiveStat getDefensiveStatById(int id) {
		return DefensiveStatDAO.getDefensiveStatById(id);
	}
	
	@Override
	public DefensiveStat getDefensiveStatByAbrev(String abrev) {
		return DefensiveStatDAO.getDefensiveStatByAbrev(abrev);
	}

	@Override
	public boolean addDefensiveStat(DefensiveStat defensiveStat) {
		return DefensiveStatDAO.addDefensiveStat(defensiveStat);
	}

	@Override
	public void updateDefensiveStat(DefensiveStat defensiveStat) {
		DefensiveStatDAO.updateDefensiveStat(defensiveStat);
	}

	@Override
	public void deleteDefensiveStat(DefensiveStat defensiveStat) {
		DefensiveStatDAO.deleteDefensiveStat(defensiveStat);
	}

}
