package com.ssa.dao;

import java.util.List;

import com.ssa.entity.GameResult;

public interface IGameResultDAO {

	List<GameResult> getAllGameResults();
	GameResult getGameResultById(int id);
	List<GameResult> getGameResultsByWeek(Integer week);
	boolean addGameResult(GameResult GameResult);
	void updateGameResult(GameResult GameResult);
	void deleteGameResult(GameResult GameResult);
}
