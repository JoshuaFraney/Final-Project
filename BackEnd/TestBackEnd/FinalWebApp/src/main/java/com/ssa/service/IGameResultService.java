package com.ssa.service;

import java.util.List;

import com.ssa.entity.GameResult;

public interface IGameResultService {

	List<GameResult> getAllGameResults();
	GameResult getGameResultById(int id);
	List<GameResult> getGameResultsByWeek(Integer week);
	boolean addGameResult(GameResult GameResult);
	void updateGameResult(GameResult GameResult);
	void deleteGameResult(GameResult GameResult);
}
