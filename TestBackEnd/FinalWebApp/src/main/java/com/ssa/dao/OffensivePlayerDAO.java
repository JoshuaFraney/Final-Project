package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.FantasyScoring;
import com.ssa.entity.OffensivePlayer;
import com.ssa.entity.OffensiveStat;

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
	public OffensivePlayer getOffensivePlayerWithStats(Integer id) {
		OffensivePlayer offensivePlayer = this.getOffensivePlayerById(id);
		hibernateTemplate.initialize(offensivePlayer.gameStats);
		return offensivePlayer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OffensivePlayer> getOffensivePlayersByPosition(String code) {
		String hql = "From OffensivePlayer as p WHERE p.position.code = '" + code + "'";
		List<OffensivePlayer> list = (List<OffensivePlayer>) hibernateTemplate.find(hql);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public OffensivePlayer getOffensivePlayer(OffensivePlayer offensivePlayer) {
		String hql = "FROM OffensivePlayer as p WHERE p.name = '" + offensivePlayer.getName() + "' AND p.team.abrev = '" + offensivePlayer.getTeam().getAbrev() + "'";
		List<OffensivePlayer> list = (List<OffensivePlayer>) hibernateTemplate.find(hql);
		if (list.isEmpty()) {
			System.out.println("NOT FOUND!");
			hibernateTemplate.save(offensivePlayer);
			return offensivePlayer;
		}
		System.out.println("Found " + list.size() + " results: " + list.get(0).getName());
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
	
	@Override
	public List<OffensivePlayer> updateRankings(FantasyScoring fantasyScoring) {
		List<OffensivePlayer> players = this.getAllWithStats();
		//Key Integer is player.id
		for (OffensivePlayer player : players) {
			double points = 0.0;
			for (OffensiveStat stats : player.getGameStats()) {
				points += fantasyScoring.getOffensiveScore(stats);
			}
			points = points / player.getGameStats().size();
			player.setAvgScore(this.round(points));
		}
		players.sort(null);
		int i = 1;
		for (OffensivePlayer p : players) {
			p.setOffRanking(i++);
			hibernateTemplate.update(p);
		}
		return players;
	}
	
	private double round(double d) {
		d *= 100;
		d = ((double) (int)d);
		return d/100;
	}

	@SuppressWarnings("unchecked")
	private List<OffensivePlayer> getAllWithStats() {
		String hql = "FROM OffensivePlayer as s ORDER BY s.id";
		List<OffensivePlayer> list = (List<OffensivePlayer>)hibernateTemplate.find(hql);
		for (OffensivePlayer player : list) {
			hibernateTemplate.initialize(player.gameStats);
		}
		return list;
	}
}