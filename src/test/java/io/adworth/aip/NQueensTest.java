package io.adworth.aip;

import static org.junit.Assert.*;

import org.junit.Test;

import io.adworth.aip.extra.Coord;
import io.adworth.aip.extra.NQueensProblem;

public class NQueensTest {
	NQueensProblem nqp = new NQueensProblem();
	
	@Test
	public void shouldBeOutsideTheBoard() {
 		nqp.board = new Coord(2,2);
 		Coord q1 = new Coord(2,2);
 		Coord q2 = new Coord(567890,23465789);
 		assertEquals(nqp.isWithinBoard(q1), true);
 		assertEquals(nqp.isWithinBoard(q2), false);
	}
	
	@Test
	public void shouldRejectNegativeOrZeroCoord() {
 		Coord q1 = new Coord(0,0);
 		Coord q2 = new Coord(-1,-12556789);
 		assertEquals(nqp.isPositiveCoord(q1), false);
 		assertEquals(nqp.isPositiveCoord(q2), false);
	}
	
	@Test
	public void shouldParseCoordCorrectly() {
		nqp.board = new Coord(4,4);
 		Coord q1 = nqp.parseCoord("                           1          ,                  3                         ");
 		Coord q2 = nqp.parseCoord("        4567          ,                  34                      ");
 		assertEquals(nqp.isWithinBoard(q1), true);
 		assertEquals(nqp.isWithinBoard(q2), false);
	}
	
	@Test
	public void shouldIndictateValidMoves() {
		nqp.board = new Coord(10,5);
 		nqp.Queens.add(new Coord(1,1));
 		nqp.Queens.add(new Coord(2,3));
 		nqp.Queens.add(new Coord(10,5));
 		assertEquals(nqp.isValidMove(new Coord(1,2)), false);
 		assertEquals(nqp.isValidMove(new Coord(2,2)), false);
 		assertEquals(nqp.isValidMove(new Coord(8,4)), true);
	}
	
	@Test
	public void shouldRunWithNoExistingQueens() {
		nqp.board = new Coord(8,5);
 		assertEquals(nqp.isValidMove(new Coord(1,2)), true);
 		assertEquals(nqp.isValidMove(new Coord(2,2)), true);
 		assertEquals(nqp.isValidMove(new Coord(8,4)), true);
	}
}
