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

import com.ssa.entity.OffensiveStat;
import com.ssa.service.IOffensiveStatService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class OffensiveStatController {

	@Autowired
	private IOffensiveStatService offensiveStatService;
	
	@RequestMapping(value="/offensiveStat",method=RequestMethod.GET)
	public ResponseEntity<List<OffensiveStat>> getAllOffensiveStats() {
		List<OffensiveStat> offensiveStats = offensiveStatService.getAllOffensiveStats();
		return new ResponseEntity<List<OffensiveStat>>(offensiveStats,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensiveStat/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<OffensiveStat> getOffensiveStatById(@PathVariable("id") Integer id) {
		OffensiveStat offensiveStat = offensiveStatService.getOffensiveStatById(id);
		if(offensiveStat!=null)
			return new ResponseEntity<OffensiveStat>(offensiveStat, HttpStatus.OK);
		else
			return new ResponseEntity<OffensiveStat>(offensiveStat, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value="/offensiveStat/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		OffensiveStat offensiveStat = offensiveStatService.getOffensiveStatById(id);
		if (offensiveStat!=null) {
			offensiveStatService.deleteOffensiveStat(offensiveStat);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/offensiveStat/update",method=RequestMethod.PUT)
	public ResponseEntity<OffensiveStat> update(@RequestBody OffensiveStat offensiveStat) {
		OffensiveStat dbOffensiveStat = offensiveStatService.getOffensiveStatById(offensiveStat.getId());
		if (dbOffensiveStat != null) {
			dbOffensiveStat.setPassCompletions(offensiveStat.getPassCompletions() + dbOffensiveStat.getPassCompletions());
			dbOffensiveStat.setPassAttempts(offensiveStat.getPassAttempts() + dbOffensiveStat.getPassAttempts());
			dbOffensiveStat.setPassYards(offensiveStat.getPassYards() + dbOffensiveStat.getPassYards());
			dbOffensiveStat.setPassTouchdowns(offensiveStat.getPassTouchdowns() + dbOffensiveStat.getPassTouchdowns());
			dbOffensiveStat.setPassInterceptions(offensiveStat.getPassInterceptions() + dbOffensiveStat.getPassInterceptions());
			dbOffensiveStat.setReceptions(offensiveStat.getReceptions() + dbOffensiveStat.getReceptions());
			dbOffensiveStat.setRecYards(offensiveStat.getRecYards() + dbOffensiveStat.getRecYards());
			dbOffensiveStat.setRecTouchdowns(offensiveStat.getRecTouchdowns() + dbOffensiveStat.getRecTouchdowns());
			dbOffensiveStat.setRushAttempts(offensiveStat.getRushAttempts() + dbOffensiveStat.getRushAttempts());
			dbOffensiveStat.setRushYards(offensiveStat.getRushYards() + dbOffensiveStat.getRushYards());
			dbOffensiveStat.setRushTouchdowns(offensiveStat.getRushTouchdowns() + dbOffensiveStat.getRushTouchdowns());
			dbOffensiveStat.setFumbles(offensiveStat.getFumbles() + dbOffensiveStat.getFumbles());
			offensiveStatService.updateOffensiveStat(offensiveStat);
			return new ResponseEntity<OffensiveStat>(dbOffensiveStat,HttpStatus.OK);
		} else
			return new ResponseEntity<OffensiveStat>(dbOffensiveStat,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/offensiveStat/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody OffensiveStat offensiveStat) {
		if(offensiveStatService.addOffensiveStat(offensiveStat))
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/offensiveStat/addList",method=RequestMethod.POST)
	public ResponseEntity<Boolean> addList(@RequestBody List<OffensiveStat> results) {
		boolean success = true;
		for(OffensiveStat result : results) {
			if(!offensiveStatService.addOffensiveStat(result)) {success = false;}
		}
		return new ResponseEntity<Boolean>(success,success?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}
}
