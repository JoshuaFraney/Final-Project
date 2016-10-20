package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.DefensiveStat;

@Transactional
@Repository
public class DefensiveStatDAO implements IDefensiveStatDAO{

	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DefensiveStat> getAllDefensiveStats() {
		String hql = "FROM DefensiveStat as s ORDER BY s.id";
		return (List<DefensiveStat>)hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DefensiveStat getDefensiveStatById(int id) {
		String hql = "FROM DefensiveStat as s where s.id = " + id;
		List<DefensiveStat> list = (List<DefensiveStat>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public DefensiveStat getDefensiveStatByAbrev(String abrev) {
		List<DefensiveStat> list = this.getAllDefensiveStats();
		for (DefensiveStat ds : list){
			if (ds.getTeam().getAbrev() == abrev)
				return ds;
		}
		return null;
	}

	@Override
	public boolean addDefensiveStat(DefensiveStat defensiveStat) {
		hibernateTemplate.save(defensiveStat);
		return true;
	}

	@Override
	public void updateDefensiveStat(DefensiveStat defensiveStat) {
		hibernateTemplate.update(defensiveStat);
	}

	@Override
	public void deleteDefensiveStat(DefensiveStat defensiveStat) {
		hibernateTemplate.delete(defensiveStat);
	}

}