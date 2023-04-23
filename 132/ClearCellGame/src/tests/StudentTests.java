package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	//this tests game constructor, setBoardWithColor, setRowWithColor
	//setColWithColor, processCell, getBoardCell and getScore
	@Test
	public void test1() {
		
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		String answer = "EMPTYEMPTYEMPTYEMPTYEMPTYEMPTYEMPTYEMPTY";
		int scoreAnswer = 18;
		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(0, BoardCell.YELLOW);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setRowWithColor(2, BoardCell.YELLOW);
		ccGame.setColWithColor(7, BoardCell.RED);
		ccGame.setColWithColor(3, BoardCell.YELLOW);
		ccGame.processCell(1, 3);
		BoardCell test1 = ccGame.getBoardCell(1, 3);
		BoardCell test2 = ccGame.getBoardCell(2, 3);
		BoardCell test3 = ccGame.getBoardCell(0, 2);
		BoardCell test4 = ccGame.getBoardCell(0, 4);
		BoardCell test5 = ccGame.getBoardCell(2, 2);
		BoardCell test6 = ccGame.getBoardCell(2, 4);
		BoardCell test7 = ccGame.getBoardCell(1, 2);
		BoardCell test8 = ccGame.getBoardCell(1, 4);
		int score = ccGame.getScore();
		String test = (test1.toString()+test2.toString()+test3.toString()+
				test4.toString()+test5.toString()+test6.toString()+test7.toString()+test8.toString());
		assertTrue(test.equals(answer) && score == scoreAnswer);
	}
	
	//tests nextAnimationStep, getMaxCols, getMaxRows, isGameOver and setBoardCell
	@Test
	public void test2 () {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		String answer = "EMPTYEMPTYEMPTYEMPTYEMPTYEMPTYEMPTYEMPTY";
		String answer2 = "RED";
		int answerArea = 64;
		int area = ccGame.getMaxCols() * ccGame.getMaxRows(); 
		ccGame.setBoardWithColor(BoardCell.RED);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.YELLOW);
		ccGame.processCell(1, 1);
		ccGame.processCell(maxRows - 1, 1);
		ccGame.nextAnimationStep();
		ccGame.setBoardCell(3, 7, BoardCell.RED);
		BoardCell test1 = ccGame.getBoardCell(7, 0);
		BoardCell test2 = ccGame.getBoardCell(7, 1);
		BoardCell test3 = ccGame.getBoardCell(7, 2);
		BoardCell test4 = ccGame.getBoardCell(7, 3);
		BoardCell test5 = ccGame.getBoardCell(7, 4);
		BoardCell test6 = ccGame.getBoardCell(7, 5);
		BoardCell test7 = ccGame.getBoardCell(7, 6);
		BoardCell test8 = ccGame.getBoardCell(7, 7);
		BoardCell test9 = ccGame.getBoardCell(3, 7);
		String test = (test1.toString()+test2.toString()+test3.toString()+
				test4.toString()+test5.toString()+test6.toString()+test7.toString()+test8.toString());
		String testTwo = test9.toString();
		Boolean gameOver = ccGame.isGameOver();
		assertTrue(area == answerArea && answer.equals(test) && gameOver == false && answer2.equals(testTwo));
	}
	
}
