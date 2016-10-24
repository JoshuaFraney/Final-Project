package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.OffensivePlayer;

@Transactional
@Repository
public class OffensivePlayerDAO implements IOffensivePlayerDAO{

	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OffensivePlayer> getAllOffensivePlayers() {
		String hql = "FROM OffensivePlayer as s ORDER BY s.id";
		return (List<OffensivePlayer>)hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OffensivePlayer getOffensivePlayerById(int id) {
		String hql = "FROM OffensivePlayer as s where s.id = " + id;
		List<OffensivePlayer> list = (List<OffensivePlayer>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean addOffensivePlayer(OffensivePlayer offensivePlayer) {
		hibernateTemplate.save(offensivePlayer);
		return true;
	}

	@Override
	public void updateOffensivePlayer(OffensivePlayer offensivePlayer) {
		hibernateTemplate.update(offensivePlayer);
	}

	@Override
	public void deleteOffensivePlayer(OffensivePlayer offensivePlayer) {
		hibernateTemplate.delete(offensivePlayer);
	}

}