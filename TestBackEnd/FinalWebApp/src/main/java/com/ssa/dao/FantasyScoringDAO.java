package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.FantasyScoring;

@Transactional
@Repository
public class FantasyScoringDAO implements IFantasyScoringDAO {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FantasyScoring> getAllFantasyScoring() {
		String hql = "From FantasyScoring as f ORDER BY f.id";
		return (List<FantasyScoring>) hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FantasyScoring getFantasyScoringById(Integer id) {
		String hql = "From FantasyScoring as f WHERE f.id = " + id;
		return ((List<FantasyScoring>) hibernateTemplate.find(hql)).get(0);
	}

	@Override
	public Boolean addFantasyScoring(FantasyScoring fantasyScoring) {
		hibernateTemplate.save(fantasyScoring);
		return true;
	}

}
