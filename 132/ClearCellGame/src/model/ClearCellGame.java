package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {
	public Random random;
	public int strategy;
	protected static int score;
	protected BoardCell processedCell;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
		score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		for (int cols = 0; cols < getMaxCols(); cols++) {
			if (board[board.length - 1][cols] != BoardCell.EMPTY) {
				return true;
			}
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		if (board[board.length - 1][0] == BoardCell.EMPTY) {
			BoardCell[][] temp = board;
			for (int rows = getMaxRows() - 1; rows > 0; rows--) {
				for (int cols = 0; cols < getMaxCols(); cols++) {
					board[rows][cols] = temp[rows - 1][cols];

				}
			}
			for (int cols = 0; cols < getMaxCols(); cols++) {
				board[0][cols] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
		}
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		processedCell = board[rowIndex][colIndex];
		board[rowIndex][colIndex] = BoardCell.EMPTY;
		score++;
		for (int i = 1; i < getMaxCols() - colIndex; i++) {
			if (board[rowIndex][colIndex + i] != processedCell) {
				break;
			} else {
				board[rowIndex][colIndex + i] = BoardCell.EMPTY;
				score++;
			}
		}
		for (int i = 1; i < colIndex + 1; i++) {
			if (board[rowIndex][colIndex - i] != processedCell && processedCell != BoardCell.EMPTY) {
				break;
			} else {
				board[rowIndex][colIndex - i] = BoardCell.EMPTY;
				score++;
			}
		}
		for (int i = 1; i < getMaxRows() - rowIndex; i++) {
			if (board[rowIndex + i][colIndex] != processedCell) {
				break;
			} else {
				board[rowIndex + i][colIndex] = BoardCell.EMPTY;
				score++;
			}
		}
		for (int i = 1; i < rowIndex + 1; i++) {
			if (board[rowIndex - i][colIndex] != processedCell) {
				break;
			} else {
				board[rowIndex - i][colIndex] = BoardCell.EMPTY;
				score++;
			}
		}

		int index;
		if (getMaxRows() >= getMaxCols()) {
			index = getMaxRows();
		} else {
			index = getMaxCols();
		}

		for (int i = 1; i < index; i++) {
			if (rowIndex + i < getMaxRows() && colIndex + i < getMaxCols()) {
				if (board[rowIndex + i][colIndex + i] != processedCell) {
					break;
				} else {
					board[rowIndex + i][colIndex + i] = BoardCell.EMPTY;
					score++;
				}
			} else {
				break;
			}
		}

		for (int i = 1; i < index; i++) {
			if (rowIndex - i >= 0 && colIndex - i >= 0) {
				if (board[rowIndex - i][colIndex - i] != processedCell) {
					break;
				} else {
					board[rowIndex - i][colIndex - i] = BoardCell.EMPTY;
					score++;
				}
			} else {
				break;
			}
		}

		for (int i = 1; i < index; i++) {
			if (rowIndex + i < getMaxRows() && colIndex - i >= 0) {
				if (board[rowIndex + i][colIndex - i] != processedCell) {
					break;
				} else {
					board[rowIndex + i][colIndex - i] = BoardCell.EMPTY;
					score++;
				}
			} else {
				break;
			}
		}

		for (int i = 1; i < index; i++) {
			if (rowIndex - i >= 0 && colIndex + i < getMaxCols()) {
				if (board[rowIndex - i][colIndex + i] != processedCell) {
					break;
				} else {
					board[rowIndex - i][colIndex + i] = BoardCell.EMPTY;
					score++;
				}
			} else {
				break;
			}
		}

		BoardCell[][] copy = board;
		for (int rows = 0; rows < getMaxRows(); rows++) {
			int colCount = 0;
			for (int cols = 0; cols < getMaxCols(); cols++) {
				if (board[rows][cols] == BoardCell.EMPTY) {
					colCount++;
				}
			}
			if (colCount == getMaxCols()) {
				for (int rows2 = rows; rows2 < getMaxRows() - 1; rows2++) {
					for (int cols = 0; cols < getMaxCols(); cols++) {
						board[rows2][cols] = copy[rows2 + 1][cols];
						board[getMaxRows() - 1][cols] = BoardCell.EMPTY;
					}
				}
			}
		}
	}
}
