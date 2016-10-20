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

import com.ssa.entity.Matchup;
import com.ssa.service.IMatchupService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class MatchupController {

	@Autowired
	private IMatchupService matchupService;
	
	@RequestMapping(value="/matchup",method=RequestMethod.GET)
	public ResponseEntity<List<Matchup>> getAllMatchups() {
		List<Matchup> matchups = matchupService.getAllMatchups();
		return new ResponseEntity<List<Matchup>>(matchups,HttpStatus.OK);
	}
	
	@RequestMapping(value="/matchup/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<Matchup> getMatchupById(@PathVariable("id") Integer id) {
		Matchup matchup = matchupService.getMatchupById(id);
		if(matchup!=null)
			return new ResponseEntity<Matchup>(matchup, HttpStatus.OK);
		else
			return new ResponseEntity<Matchup>(matchup, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/matchup/week/{week}",method=RequestMethod.GET)
	public ResponseEntity<List<Matchup>> getMatchupsByWeek(@PathVariable("week") Integer week) {
		List<Matchup> matchups = matchupService.getMatchupsByWeek(week);
		return new ResponseEntity<List<Matchup>>(matchups,HttpStatus.OK);
	}
	
	@RequestMapping(value="/matchup/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		Matchup matchup = matchupService.getMatchupById(id);
		if (matchup!=null) {
			matchupService.deleteMatchup(matchup);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/matchup/update",method=RequestMethod.PUT)
	public ResponseEntity<Matchup> update(@RequestBody Matchup matchup) {
		Matchup dbMatchup = matchupService.getMatchupById(matchup.getId());
		if (dbMatchup != null) {
			dbMatchup.setWeek(matchup.getWeek());
			dbMatchup.setHomeTeam(matchup.getHomeTeam());
			dbMatchup.setAwayTeam(matchup.getAwayTeam());
			dbMatchup.setIsFinal(matchup.getIsFinal());
			matchupService.updateMatchup(matchup);
			return new ResponseEntity<Matchup>(dbMatchup,HttpStatus.OK);
		} else
			return new ResponseEntity<Matchup>(dbMatchup,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/matchup/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody Matchup matchup) {
		if(matchupService.addMatchup(matchup))
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/matchup/addList",method=RequestMethod.POST)
	public ResponseEntity<Boolean> addList(@RequestBody List<Matchup> matchups) {
		boolean success = true;
		for(Matchup matchup : matchups) {
			if(!matchupService.addMatchup(matchup)) {success = false;}
		}
		return new ResponseEntity<Boolean>(success,success?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
}
