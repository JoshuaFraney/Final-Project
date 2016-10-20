package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IOffensiveStatDAO;
import com.ssa.entity.OffensiveStat;

@Service
public class OffensiveStatService implements IOffensiveStatService {

	@Autowired
	private IOffensiveStatDAO OffensiveStatDAO;
	
	@Override
	public List<OffensiveStat> getAllOffensiveStats() {
		return OffensiveStatDAO.getAllOffensiveStats();
	}

	@Override
	public OffensiveStat getOffensiveStatById(int id) {
		return OffensiveStatDAO.getOffensiveStatById(id);
	}

	@Override
	public boolean addOffensiveStat(OffensiveStat offensiveStat) {
		return OffensiveStatDAO.addOffensiveStat(offensiveStat);
	}

	@Override
	public void updateOffensiveStat(OffensiveStat offensiveStat) {
		OffensiveStatDAO.updateOffensiveStat(offensiveStat);
	}

	@Override
	public void deleteOffensiveStat(OffensiveStat offensiveStat) {
		OffensiveStatDAO.deleteOffensiveStat(offensiveStat);
	}

}
