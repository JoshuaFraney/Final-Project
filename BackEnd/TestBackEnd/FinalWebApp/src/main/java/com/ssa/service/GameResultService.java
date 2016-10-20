package com.ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.dao.IGameResultDAO;
import com.ssa.entity.GameResult;

@Service
public class GameResultService implements IGameResultService {

	@Autowired
	private IGameResultDAO GameResultDAO;
	
	@Override
	public List<GameResult> getAllGameResults() {
		return GameResultDAO.getAllGameResults();
	}

	@Override
	public GameResult getGameResultById(int id) {
		return GameResultDAO.getGameResultById(id);
	}
	
	@Override
	public List<GameResult> getGameResultsByWeek(Integer week) {
		return GameResultDAO.getGameResultsByWeek(week);
	}

	@Override
	public boolean addGameResult(GameResult gameResult) {
		if(gameResult.getTie()) {
			gameResult.getWinTeam().addTie();
			gameResult.getLoseTeam().addTie();
		} else {
			gameResult.getWinTeam().addWin();
			gameResult.getLoseTeam().addLoss();
		}
		return GameResultDAO.addGameResult(gameResult);
	}

	@Override
	public void updateGameResult(GameResult gameResult) {
		GameResultDAO.updateGameResult(gameResult);
	}

	@Override
	public void deleteGameResult(GameResult gameResult) {
		GameResultDAO.deleteGameResult(gameResult);
	}

}