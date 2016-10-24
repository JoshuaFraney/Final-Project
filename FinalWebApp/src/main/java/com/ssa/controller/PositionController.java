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
import com.ssa.entity.OffensivePlayer;
import com.ssa.entity.Position;
import com.ssa.service.IPositionService;

@CrossOrigin(origins = {"http://localhost:8081","null"})
@RestController
@RequestMapping("/")
public class PositionController {

	@Autowired
	private IPositionService positionService;
	
	@RequestMapping(value="/position",method=RequestMethod.GET)
	public ResponseEntity<List<Position>> getAllPositions() {
		List<Position> positions = positionService.getAllPositions();
		return new ResponseEntity<List<Position>>(positions,HttpStatus.OK);
	}
	
	@RequestMapping(value="/position/players/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<OffensivePlayer>> getPlayersByPosition(@PathVariable("id") Integer id) {
		List<OffensivePlayer> players = positionService.getPositionById(id).getPlayersByPosition();
		return new ResponseEntity<List<OffensivePlayer>>(players, HttpStatus.OK);
	}
	
	@RequestMapping(value="/position/{code}",method=RequestMethod.GET)
	public ResponseEntity<Position> getPositionByCode(@PathVariable("code") String code) {
		Position position = positionService.getPositionByCode(code);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}
	
	@RequestMapping(value="/position/id/{id}",method=RequestMethod.GET)
	public ResponseEntity<Position> getPositionById(@PathVariable("id") Integer id) {
		Position position = positionService.getPositionById(id);
		if(position!=null)
			return new ResponseEntity<Position>(position, HttpStatus.OK);
		else
			return new ResponseEntity<Position>(position, HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value="/position/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		Position position = positionService.getPositionById(id);
		if (position!=null) {
			positionService.deletePosition(position);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/position/update",method=RequestMethod.PUT)
	public ResponseEntity<Position> update(@RequestBody Position position) {
		Position dbPosition = positionService.getPositionById(position.getId());
		if (dbPosition != null) {
			dbPosition.setCode(position.getCode());
			dbPosition.setDescription(position.getDescription());
			positionService.updatePosition(position);
			return new ResponseEntity<Position>(dbPosition,HttpStatus.OK);
		} else
			return new ResponseEntity<Position>(dbPosition,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/position/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody Position position) {
		if(positionService.addPosition(position))
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
	}
}
