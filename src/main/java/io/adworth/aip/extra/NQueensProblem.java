package io.adworth.aip.extra;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.adworth.aip.helper.Parser;
import io.adworth.aip.helper.ResponseMessage;

@RestController("/NQueens")
public class NQueensProblem {
	static final int X_START = 1;
	static final int Y_START = 1;
	static Coord board;
	static ArrayList<Coord> Queens;
	static Coord extra;
	
	@PostMapping()
	private ResponseEntity<?> solve(@RequestBody String json_str) {
	
		String boardDimensions = Parser.json2Str(json_str, "dimensions");
		ArrayList<String> existingQueens = Parser.json2StrArray(json_str, "queens");
		String newQueen = Parser.json2Str(json_str, "new");
		String strResponse;
		
		if (boardDimensions == null || existingQueens == null || newQueen == null) {
			return Parser.parseError();
		}
		
		board = parseCoord(boardDimensions);
		if (isPositiveCoord(board) == false) {
			return ResponseMessage.response("Bad board dimension.", HttpStatus.BAD_REQUEST);
		}
		
		Queens = new ArrayList<Coord>();
		for (String Queen: existingQueens) {
			Coord q = parseCoord(Queen);
			if (isWithinBoard(q) == false) {
				return ResponseMessage.response("One or more existing Queen(s) is/are out of boundary.", HttpStatus.BAD_REQUEST);
			} else if (isValidMove(q) == false) {
				return ResponseMessage.response("Bad existing Queens setup. Position (" + q.getX() + ", " + q.getY() + ") is not available for placement.", HttpStatus.BAD_REQUEST);
			}
			Queens.add(q);

		}
		
		extra = parseCoord(newQueen);
		if (isWithinBoard(extra) == false) {
			return ResponseMessage.response("The new Queen to be added is out of boundary.", HttpStatus.BAD_REQUEST);
		}
		
		if (isValidMove(extra)) {
			strResponse = "VALID";
		} else {
			strResponse = "INVALID";
		}
		
		return ResponseMessage.response("The move (" + extra.getX() + ", " + extra.getY() + ") is " + strResponse, HttpStatus.OK);
	}
	
	private Coord parseCoord(String Queen){
		String[] coord = Queen.split(",");
		Coord newQueen = new Coord(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
		return newQueen;
	}
	
	private boolean isWithinBoard(Coord c){
		if (isPositiveCoord(c) && c.getX() <= board.getX() && c.getY() <= board.getY()) {
			return true;
		}
		return false;
	}
	
	private boolean isPositiveCoord(Coord c){
		if (c.getX() >= X_START && c.getY() >= Y_START) {
			return true;
		}
		return false;
	}
	
	private boolean isValidMove(Coord c){
		for (Coord q: Queens){
			int xDiff = Math.abs(q.getX() - c.getX());
			int yDiff = Math.abs(q.getY() - c.getY());
			if (q.getX() == c.getX() || q.getY() == c.getY() || xDiff == yDiff) {
				return false;
			}
		}
		return true;
	}
	
}


