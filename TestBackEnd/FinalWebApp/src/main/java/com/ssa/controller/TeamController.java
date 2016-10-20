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

import com.ssa.entity.Team;
import com.ssa.entity.Matchup;
import com.ssa.service.ITeamService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class TeamController {

	@Autowired
	private ITeamService teamService;
	
	@RequestMapping(value="/team",method=RequestMethod.GET)
	public ResponseEntity<List<Team>> getAllTeams() {
		List<Team> teams = teamService.getAllTeams();
		return new ResponseEntity<List<Team>>(teams,HttpStatus.OK);
	}
	
	@RequestMapping(value="/team/homegames/{abrev}",method=RequestMethod.GET)
	public ResponseEntity<List<Matchup>> getHomeGames(@PathVariable("abrev") String abrev) {
		List<Matchup> homeGames = teamService.getTeamByAbrev(abrev).getHomeGames();
		return new ResponseEntity<List<Matchup>>(homeGames, HttpStatus.OK);
	}
	
	@RequestMapping(value="/team/awaygames/{abrev}",method=RequestMethod.GET)
	public ResponseEntity<List<Matchup>> getAwayGames(@PathVariable("abrev") String abrev) {
		List<Matchup> awayGames = teamService.getTeamByAbrev(abrev).getAwayGames();
		return new ResponseEntity<List<Matchup>>(awayGames, HttpStatus.OK);
	}
	
	@RequestMapping(value="/team/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<Team> getTeamById(@PathVariable("id") Integer id) {
		Team team = teamService.getTeamById(id);
		if(team!=null)
			return new ResponseEntity<Team>(team, HttpStatus.OK);
		else
			return new ResponseEntity<Team>(team, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="team/abrev/{abrev}",method=RequestMethod.GET)
	public ResponseEntity<Team> getTeamByAbrev(@PathVariable("abrev") String abrev) {
		Team team = teamService.getTeamByAbrev(abrev);
		if(team!=null)
			return new ResponseEntity<Team>(team, HttpStatus.OK);
		else
			return new ResponseEntity<Team>(team, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/team/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		Team team = teamService.getTeamById(id);
		if (team!=null) {
			teamService.deleteTeam(team);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/team/update",method=RequestMethod.PUT)
	public ResponseEntity<Team> update(@RequestBody Team team) {
		Team dbTeam = teamService.getTeamById(team.getId());
		if (dbTeam != null) {
			dbTeam.setDivision(team.getDivision());
			dbTeam.setTeamName(team.getTeamName());
			dbTeam.setWins(team.getWins());
			dbTeam.setLosses(team.getLosses());
			dbTeam.setTies(team.getTies());
			dbTeam.setOvrRank(team.getOvrRank());
			teamService.updateTeam(team);
			return new ResponseEntity<Team>(dbTeam,HttpStatus.OK);
		} else
			return new ResponseEntity<Team>(dbTeam,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/team/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody Team team) {
		if(teamService.addTeam(team))
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
	}
	
}
