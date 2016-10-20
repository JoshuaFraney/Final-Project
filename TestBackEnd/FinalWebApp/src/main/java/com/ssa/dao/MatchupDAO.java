package com.ssa.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.Matchup;

@Transactional
@Repository
public class MatchupDAO implements IMatchupDAO{

	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Matchup> getAllMatchups() {
		String hql = "FROM Matchup as s ORDER BY s.id";
		List<Matchup> list =  (List<Matchup>)hibernateTemplate.find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Matchup getMatchupById(int id) {
		String hql = "FROM Matchup as s where s.id = " + id;
		List<Matchup> list = (List<Matchup>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Matchup> getMatchupsByWeek(Integer week) {
		String hql = "FROM Matchup as m where m.week = " + week;
		return (List<Matchup>)hibernateTemplate.find(hql);
	}

	@Override
	public boolean addMatchup(Matchup matchup) {
		hibernateTemplate.save(matchup);
		return true;
	}

	@Override
	public void updateMatchup(Matchup matchup) {
		hibernateTemplate.update(matchup);
	}

	@Override
	public void deleteMatchup(Matchup matchup) {
		hibernateTemplate.delete(matchup);
	}

}