package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.OffensivePlayer;
import com.ssa.entity.Position;

@Transactional
@Repository
public class PositionDAO implements IPositionDAO {
	
	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getAllPositions() {
		String hql = "FROM Position as p ORDER BY p.id";
		return (List<Position>)hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position getPositionById(int id) {
		String hql = "FROM Position as p WHERE p.id = " + id;
		List<Position> list = (List<Position>)hibernateTemplate.find(hql).get(0);
		if(list.isEmpty()) {return null;}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Position getPositionByCode(String code) {
		String hql = "FROM Position as p WHERE p.code = " + code;
		List<Position> list = (List<Position>)hibernateTemplate.find(hql);
		if(list.isEmpty()) {return null;}
		return list.get(0);
	}
	
	@Override
	public List<OffensivePlayer> getPlayersByPosition(String code) {
		Position position = this.getPositionByCode(code);
		hibernateTemplate.initialize(position.playersByPosition);
		return position.getPlayersByPosition();
	}

	@Override
	public boolean addPosition(Position position) {
		hibernateTemplate.save(position);
		return true;
	}

	@Override
	public void updatePosition(Position position) {
		hibernateTemplate.update(position);
	}

	@Override
	public void deletePosition(Position position) {
		hibernateTemplate.delete(position);
	}

}
