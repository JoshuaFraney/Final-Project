package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.Team;

@Transactional
@Repository
public class TeamDAO implements ITeamDAO{

	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Team> getAllTeams() {
		String hql = "FROM Team as s ORDER BY s.id";
		return (List<Team>)hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Team getTeamById(int id) {
		String hql = "FROM Team as t where t.id = " + id;
		List<Team> list = (List<Team>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Team getTeamByAbrev(String abrev) {
		String hql = "FROM Team as t where t.abrev = " + abrev;
		List<Team> list = (List<Team>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean addTeam(Team team) {
		hibernateTemplate.save(team);
		return true;
	}

	@Override
	public void updateTeam(Team team) {
		hibernateTemplate.update(team);
	}

	@Override
	public void deleteTeam(Team team) {
		hibernateTemplate.delete(team);
	}

}
