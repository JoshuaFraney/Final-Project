package com.ssa.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.ssa.entity.FantasyScoring;
import com.ssa.entity.OffensivePlayer;
import com.ssa.entity.OffensiveStat;
import com.ssa.entity.Team;
import com.ssa.service.IFantasyScoringService;
import com.ssa.service.IOffensivePlayerService;
import com.ssa.service.ITeamService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class OffensivePlayerController {
	
	@Autowired
	private IOffensivePlayerService offensivePlayerService;
	
	@Autowired 
	private ITeamService teamService;
	
	@Autowired
	private IFantasyScoringService fantasyScoringService;
	
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
	
	@RequestMapping(value="/offensivePlayer/stats/playerId/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<OffensiveStat>> getStatsByPlayer(@PathVariable("id") Integer id) {
		OffensivePlayer offensivePlayer = offensivePlayerService.getOffensivePlayerWithStats(id);
		return new ResponseEntity<List<OffensiveStat>>(offensivePlayer.getGameStats(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/stats/season/playerId/{id}",method=RequestMethod.GET)
	public ResponseEntity<OffensiveStat> getSeasonStats(@PathVariable("id") Integer id) {
		OffensivePlayer player = offensivePlayerService.getOffensivePlayerWithStats(id);
		List<OffensiveStat> list = player.getGameStats();
		OffensiveStat seasonStats = new OffensiveStat(null,0,0,0,0,0,0,0,0,0,0,0,0);
		for (OffensiveStat stat : list) {
			seasonStats.addStats(stat);
		}
		return new ResponseEntity<OffensiveStat>(seasonStats,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/scores/playerId/{id}/scoring/{scoreId}",method=RequestMethod.GET)
	public ResponseEntity<List<Double>> getScoresByPlayer(@PathVariable("id") Integer id,@PathVariable("scoreId") Integer scoreId) {
		OffensivePlayer offensivePlayer = offensivePlayerService.getOffensivePlayerWithStats(id);
		FantasyScoring fantasyScoring = fantasyScoringService.getFantasyScoringById(scoreId);
		List<Double> scores = new ArrayList<Double>();
		for(OffensiveStat stat : offensivePlayer.getGameStats()) {
			scores.add(fantasyScoring.getOffensiveScore(stat));
		}
		return new ResponseEntity<List<Double>>(scores,HttpStatus.OK);
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
	
	@RequestMapping(value="/offensivePlayer/getRanking/score/{id}/top/{bool}/number/{number}",method=RequestMethod.GET)
	public ResponseEntity<List<OffensivePlayer>> updateRankings(@PathVariable("id") Integer id,@PathVariable("bool") Boolean top,@PathVariable("number") Integer number) {
		List<OffensivePlayer> list = offensivePlayerService.updateRankings(fantasyScoringService.getFantasyScoringById(id));
		List<OffensivePlayer> returnList = top?list.subList(0,number):list.subList(list.size()-number,list.size());
		return new ResponseEntity<List<OffensivePlayer>>(returnList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/offensivePlayer/getRanking/score/{id}/top/{bool}/qb/{qbs}/wr/{wrs}/rb/{rbs}/te/{tes}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,List<OffensivePlayer>>> getRecPlayers(@PathVariable("id") Integer id,@PathVariable("bool") Boolean top,
			@PathVariable("qbs") Integer qbs,@PathVariable("wrs") Integer wrs,
			@PathVariable("rbs") Integer rbs,@PathVariable("tes") Integer tes) {
		List<OffensivePlayer> list = offensivePlayerService.updateRankings(fantasyScoringService.getFantasyScoringById(id));
		if(!top) {Collections.reverse(list);}
		Map<String,List<OffensivePlayer>> returnMap = new HashMap<String,List<OffensivePlayer>>();
		returnMap.put("QB",new ArrayList<OffensivePlayer>());
		returnMap.put("WR",new ArrayList<OffensivePlayer>());
		returnMap.put("RB",new ArrayList<OffensivePlayer>());
		returnMap.put("TE",new ArrayList<OffensivePlayer>());
		Map<String,Integer> added = new HashMap<String,Integer>();
		added.put("QB",0);
		added.put("WR",0);
		added.put("RB",0);
		added.put("TE",0);
		Map<String,Integer> totals = new HashMap<String,Integer>();
		totals.put("QB",qbs);
		totals.put("WR",wrs);
		totals.put("RB",rbs);
		totals.put("TE",tes);
		int i = 0;
		while(added.get("QB")<qbs || added.get("WR")<wrs || added.get("RB")<rbs || added.get("TE")<tes) {
			OffensivePlayer player = list.get(i++);
			if(added.get(player.getPosition().getCode())<totals.get(player.getPosition().getCode())) {
				returnMap.get(player.getPosition().getCode()).add(player);
				Integer wtf = added.get(player.getPosition().getCode());
				added.replace(player.getPosition().getCode(), wtf, ++wtf);
			}
		}
		return new ResponseEntity<Map<String,List<OffensivePlayer>>>(returnMap,HttpStatus.OK);
	}

}
