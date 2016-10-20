package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.GameResult;

@Transactional
@Repository
public class GameResultDAO implements IGameResultDAO{

	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GameResult> getAllGameResults() {
		String hql = "FROM GameResult as s ORDER BY s.id";
		return (List<GameResult>)hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameResult getGameResultById(int id) {
		String hql = "FROM GameResult as s where s.id = " + id;
		List<GameResult> list = (List<GameResult>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GameResult> getGameResultsByWeek(Integer week) {
		String hql = "FROM GameResult as r where r.week = " + week;
		return (List<GameResult>) hibernateTemplate.find(hql);
	}

	@Override
	public boolean addGameResult(GameResult gameResult) {
		hibernateTemplate.save(gameResult);
		return true;
	}

	@Override
	public void updateGameResult(GameResult gameResult) {
		hibernateTemplate.update(gameResult);
	}

	@Override
	public void deleteGameResult(GameResult gameResult) {
		hibernateTemplate.delete(gameResult);
	}

}