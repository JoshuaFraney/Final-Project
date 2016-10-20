package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.OffensiveStat;

@Transactional
@Repository
public class OffensiveStatDAO implements IOffensiveStatDAO{

	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OffensiveStat> getAllOffensiveStats() {
		String hql = "FROM OffensiveStat as s ORDER BY s.id";
		return (List<OffensiveStat>)hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OffensiveStat getOffensiveStatById(int id) {
		String hql = "FROM OffensiveStat as s where s.id = " + id;
		List<OffensiveStat> list = (List<OffensiveStat>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean addOffensiveStat(OffensiveStat offensiveStat) {
		hibernateTemplate.save(offensiveStat);
		return true;
	}

	@Override
	public void updateOffensiveStat(OffensiveStat offensiveStat) {
		hibernateTemplate.update(offensiveStat);
	}

	@Override
	public void deleteOffensiveStat(OffensiveStat OffensiveStat) {
		hibernateTemplate.delete(OffensiveStat);
	}

}