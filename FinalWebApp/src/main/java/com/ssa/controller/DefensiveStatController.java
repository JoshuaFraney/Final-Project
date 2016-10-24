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

import com.ssa.entity.DefensiveStat;
import com.ssa.service.IDefensiveStatService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class DefensiveStatController {

	@Autowired
	private IDefensiveStatService defensiveStatService;
	
	@RequestMapping(value="/defensiveStat",method=RequestMethod.GET)
	public ResponseEntity<List<DefensiveStat>> getAllDefensiveStats() {
		List<DefensiveStat> defensiveStats = defensiveStatService.getAllDefensiveStats();
		return new ResponseEntity<List<DefensiveStat>>(defensiveStats,HttpStatus.OK);
	}
	
	@RequestMapping(value="/defensiveStat/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<DefensiveStat> getDefensiveStatById(@PathVariable("id") Integer id) {
		DefensiveStat defensiveStat = defensiveStatService.getDefensiveStatById(id);
		if(defensiveStat!=null)
			return new ResponseEntity<DefensiveStat>(defensiveStat, HttpStatus.OK);
		else
			return new ResponseEntity<DefensiveStat>(defensiveStat, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/defensiveStat/abrev/{abrev}",method=RequestMethod.GET)
	public ResponseEntity<DefensiveStat> getDefensiveStatByAbrev(@PathVariable("abrev") String abrev) {
		DefensiveStat defensiveStat = defensiveStatService.getDefensiveStatByAbrev(abrev);
		if(defensiveStat!=null)
			return new ResponseEntity<DefensiveStat>(defensiveStat,HttpStatus.OK);
		else
			return new ResponseEntity<DefensiveStat>(defensiveStat,HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value="/defensiveStat/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		DefensiveStat defensiveStat = defensiveStatService.getDefensiveStatById(id);
		if (defensiveStat!=null) {
			defensiveStatService.deleteDefensiveStat(defensiveStat);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/defensiveStat/update",method=RequestMethod.PUT)
	public ResponseEntity<DefensiveStat> update(@RequestBody DefensiveStat defensiveStat) {
		DefensiveStat dbDefensiveStat = defensiveStatService.getDefensiveStatByAbrev(defensiveStat.getTeam().getAbrev());
		if (dbDefensiveStat != null) {
			dbDefensiveStat.setPassYardsAllowed(defensiveStat.getPassYardsAllowed() + dbDefensiveStat.getPassYardsAllowed());
			dbDefensiveStat.setRushYardsAllowed(defensiveStat.getRushYardsAllowed() + dbDefensiveStat.getRushYardsAllowed());
			dbDefensiveStat.setPointsAllowed(defensiveStat.getPointsAllowed() + dbDefensiveStat.getPointsAllowed());
			dbDefensiveStat.setSacks(defensiveStat.getSacks() + dbDefensiveStat.getSacks());
			dbDefensiveStat.setInterceptions(defensiveStat.getInterceptions() + dbDefensiveStat.getInterceptions());
			dbDefensiveStat.setFumbleRcvry(defensiveStat.getFumbleRcvry() + dbDefensiveStat.getFumbleRcvry());
			defensiveStatService.updateDefensiveStat(defensiveStat);
			return new ResponseEntity<DefensiveStat>(dbDefensiveStat,HttpStatus.OK);
		} else
			return new ResponseEntity<DefensiveStat>(dbDefensiveStat,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/defensiveStat/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody DefensiveStat defensiveStat) {
		if(defensiveStatService.addDefensiveStat(defensiveStat))
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/defensiveStat/addList",method=RequestMethod.POST)
	public ResponseEntity<Boolean> addList(@RequestBody List<DefensiveStat> results) {
		boolean success = true;
		for(DefensiveStat result : results) {
			if(!defensiveStatService.addDefensiveStat(result)) {success = false;}
		}
		return new ResponseEntity<Boolean>(success,success?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
	
}
