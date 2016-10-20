package com.ssa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.entity.GameResult;
import com.ssa.service.IGameResultService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class GameResultController {

	@Autowired
	private IGameResultService gameResultService;
	
	@RequestMapping(value="/gameResult",method=RequestMethod.GET)
	public ResponseEntity<List<GameResult>> getAllGameResults() {
		List<GameResult> gameResults = gameResultService.getAllGameResults();
		return new ResponseEntity<List<GameResult>>(gameResults,HttpStatus.OK);
	}
	
	@RequestMapping(value="/gameResult/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<GameResult> getGameResultById(@PathVariable("id") Integer id) {
		GameResult gameResult = gameResultService.getGameResultById(id);
		if(gameResult!=null)
			return new ResponseEntity<GameResult>(gameResult, HttpStatus.OK);
		else
			return new ResponseEntity<GameResult>(gameResult, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/gameResult/week/{week}",method=RequestMethod.GET)
	public ResponseEntity<List<GameResult>> getGameResultsByWeek(@PathVariable("week") Integer week) {
		List<GameResult> gameResults = gameResultService.getGameResultsByWeek(week);
		return new ResponseEntity<List<GameResult>>(gameResults,HttpStatus.OK);
	}
	
	@RequestMapping(value="/gameResult/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		GameResult gameResult = gameResultService.getGameResultById(id);
		if (gameResult!=null) {
			gameResultService.deleteGameResult(gameResult);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/gameResult/update",method=RequestMethod.PUT)
	public ResponseEntity<GameResult> update(@RequestBody GameResult gameResult) {
		GameResult dbGameResult = gameResultService.getGameResultById(gameResult.getId());
		if (dbGameResult != null) {
			dbGameResult.setMatchup(gameResult.getMatchup());
			dbGameResult.setWinTeam(gameResult.getWinTeam());
			dbGameResult.setLoseTeam(gameResult.getLoseTeam());
			dbGameResult.setTie(gameResult.getTie());
			gameResultService.updateGameResult(gameResult);
			return new ResponseEntity<GameResult>(dbGameResult,HttpStatus.OK);
		} else
			return new ResponseEntity<GameResult>(dbGameResult,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/gameResult/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody GameResult gameResult) {
		if(gameResultService.addGameResult(gameResult))
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/gameResult/addList",method=RequestMethod.POST)
	public ResponseEntity<Boolean> addList(@RequestBody List<GameResult> results) {
		boolean success = true;
		for(GameResult result : results) {
			if(!gameResultService.addGameResult(result)) {success = false;}
		}
		return new ResponseEntity<Boolean>(success,success?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
}
