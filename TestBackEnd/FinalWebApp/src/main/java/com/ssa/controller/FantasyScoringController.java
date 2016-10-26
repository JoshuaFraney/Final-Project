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

import com.ssa.service.IFantasyScoringService;
import com.ssa.entity.FantasyScoring;
import com.ssa.entity.OffensiveStat;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class FantasyScoringController {
	
	@Autowired
	IFantasyScoringService fantasyScoringService;
	
	@RequestMapping(value="/fantasyScoring",method=RequestMethod.GET)
	public ResponseEntity<List<FantasyScoring>> getAllFantasyScoring() {
		return new ResponseEntity<List<FantasyScoring>>(fantasyScoringService.getAllFantasyScoring(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/fantasyScoring/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<FantasyScoring> getFantasyScoringById(@PathVariable("id") Integer id) {
		return new ResponseEntity<FantasyScoring>(fantasyScoringService.getFantasyScoringById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/fantasyScoring/offensiveScore/id/{id}",method=RequestMethod.POST)
	public ResponseEntity<Double> getScore(@PathVariable("id") Integer id, @RequestBody OffensiveStat offensiveStat) {
		return new ResponseEntity<Double>(fantasyScoringService.getFantasyScoringById(id).getOffensiveScore(offensiveStat),HttpStatus.OK);
	}

	@RequestMapping(value="/fantasyScoring/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody FantasyScoring fantasyScoring) {
		fantasyScoringService.addFantasyScoring(fantasyScoring);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
}
