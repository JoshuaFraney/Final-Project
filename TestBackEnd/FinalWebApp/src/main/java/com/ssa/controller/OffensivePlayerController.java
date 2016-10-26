package com.ssa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.entity.OffensivePlayer;
import com.ssa.entity.Team;
import com.ssa.service.IOffensivePlayerService;
import com.ssa.service.ITeamService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class OffensivePlayerController {
	
	@Autowired
	private IOffensivePlayerService offensivePlayerService;
	
	@Autowired ITeamService teamService;
	
	@RequestMapping(value="/offensivePlayer",method=RequestMethod.GET)
	public ResponseEntity<List<OffensivePlayer>> getAllOffensivePlayers() {
		List<OffensivePlayer> offensivePlayers = offensivePlayerService.getAllOffensivePlayers();
		return new ResponseEntity<List<OffensivePlayer>>(offensivePlayers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/byTeam",method=RequestMethod.GET)
	public ResponseEntity<Map<String,List<OffensivePlayer>>> getAllRosters() {
		Map<String,List<OffensivePlayer>> map = new HashMap<String,List<OffensivePlayer>>();
		List<Team> teams = teamService.getAllTeams();
		for(Team team : teams) {
			map.put(team.getAbrev(), team.getRoster());
		}
		return new ResponseEntity<Map<String,List<OffensivePlayer>>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/team/{team}",method=RequestMethod.GET)
	public ResponseEntity<List<OffensivePlayer>> getOffensivePlayersByTeam(@PathVariable("team") String team) {
		List<OffensivePlayer> offensivePlayers = offensivePlayerService.getOffensivePlayersByTeam(team);
		return new ResponseEntity<List<OffensivePlayer>>(offensivePlayers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/position/{code}",method=RequestMethod.GET) 
	public ResponseEntity<List<OffensivePlayer>> getOffensivePlayerByPosition(@PathVariable("code") String code) {
		List<OffensivePlayer> offensivePlayers = offensivePlayerService.getOffensivePlayersByPosition(code);
		return new ResponseEntity<List<OffensivePlayer>>(offensivePlayers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<OffensivePlayer> getOffensivePlayerById(@PathVariable("id") Integer id) {
		OffensivePlayer offensivePlayer = offensivePlayerService.getOffensivePlayerById(id);
		if(offensivePlayer!=null)
			return new ResponseEntity<OffensivePlayer>(offensivePlayer, HttpStatus.OK);
		else
			return new ResponseEntity<OffensivePlayer>(offensivePlayer, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/offensivePlayer/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		OffensivePlayer offensivePlayer = offensivePlayerService.getOffensivePlayerById(id);
		if (offensivePlayer!=null) {
			offensivePlayerService.deleteOffensivePlayer(offensivePlayer);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/offensivePlayer/update",method=RequestMethod.PUT)
	public ResponseEntity<OffensivePlayer> update(@RequestBody OffensivePlayer offensivePlayer) {
		OffensivePlayer dbOffensivePlayer = offensivePlayerService.getOffensivePlayerById(offensivePlayer.getId());
		if (dbOffensivePlayer != null) {
			dbOffensivePlayer.setName(offensivePlayer.getName());
			dbOffensivePlayer.setOffRanking(offensivePlayer.getOffRanking());
			offensivePlayerService.updateOffensivePlayer(offensivePlayer);
			return new ResponseEntity<OffensivePlayer>(dbOffensivePlayer,HttpStatus.OK);
		} else
			return new ResponseEntity<OffensivePlayer>(dbOffensivePlayer,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/offensivePlayer/add",method=RequestMethod.POST)
	public ResponseEntity<OffensivePlayer> add(@RequestBody OffensivePlayer offensivePlayer) {
		return new ResponseEntity<OffensivePlayer>(offensivePlayerService.getOffensivePlayer(offensivePlayer),HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/addList",method=RequestMethod.POST)
	public ResponseEntity<Boolean> addList(@RequestBody List<OffensivePlayer> results) {
		boolean success = true;
		for(OffensivePlayer result : results) {
			if(!offensivePlayerService.addOffensivePlayer(result)) {success = false;}
		}
		return new ResponseEntity<Boolean>(success,success?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}

}
