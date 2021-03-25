package uk.ac.aston.jpd.tut1.tictactoe.game;

import java.util.ArrayList;

/**
 * Represents the 3x3 board in a Tic-Tac-Toe game. The players mark positions in
 * the board, and the winner is the first player that occupies an entire row, an
 * entire column, or one of the diagonals.
 *
 * @author Antonio García-Domínguez
 */
public class Board {

	/**
	 * Number of rows and columns for the Tic-Tac-Toe board.
	 */
	public static final int SIZE = 3;

	private Player[][] board = new Player[SIZE][SIZE];


	/**
	 * Returns the {@link Player} who is occupying the cell at column {@code column}
	 * and row {@code row}, or {@code null} if no {@link Player} is occupying it.
	 *
	 * @param column Zero-based index for the column.
	 * @param row    Zero-based index for the row.
	 * @return {@link Player} occupying the position, or {@code null} if no
	 *         {@link Player} is occupying it.
	 */
	public Player getCell(int column, int row) {
		// TODO
		return board[column][row];
		
	}

	/**
	 * If the cell at column {@code column} and row {@code row} is free, marks it as
	 * occupied by {@code player} and returns {@code true}. Otherwise, it returns
	 * {@code false}.
	 *
	 * @param column Zero-based index for the column.
	 * @param row    Zero-based index for the row.
	 * @param player {@link Player} that is trying to occupy the cell.
	 * @return {@code true} if the cell was not occupied until now, or {@code false}
	 *         if the cell was already occupied.
	 */
	public boolean occupyCell(int column, int row, Player player) {
		boolean wasNotOccupied = board[column][row] == null;
		if(wasNotOccupied) {
			board[column][row] = player;
		}
		return wasNotOccupied;
	}

	/**
	 * Returns the player who has won the game, or {@code null} if nobody has won
	 * yet.
	 *
	 * @return {@link Player} that won the game, or {@code null} if nobody won yet.
	 */
	public Player getWinner() {
		Player winner = null;
		// if winner by columns, return that
		winner = getWinnerInColumns();
		// if winner by rows, return that
		if(winner == null) {
			winner = getWinnerInRows();
		}
		// if winner by major diagonal, return that
		if(winner == null) {
			winner = getWinnerInMajorDiagonal();
		}
		// if winner by minor diagonal, return that
		if(winner == null) {
			winner = getWinnerInMinorDiagonal();
		}
		// no winner
		return winner;
	}

	public Player getWinnerInColumns() {
		for(int iColumn = 0; iColumn < SIZE; iColumn++) {
			Player player = getCell(iColumn, 0);
			if(player != null) {
				boolean hasWon = true;
				for(int iRow = 1; iRow < SIZE; iRow++) {
					if(getCell(iColumn, iRow) != player) {
						hasWon = false;
					}
				}
				if(hasWon) {
					return player;
				}
			}
		}
		return null;
	}

	public Player getWinnerInRows() {
		for(int iRow = 0; iRow < SIZE; iRow++) {
			Player player = getCell(0, iRow);
			if(player != null) {
				boolean hasWon = true;
				for(int iColumn = 1; iColumn < SIZE; iColumn++) {
					if(getCell(iColumn, iRow) != player) {
						hasWon = false;
					}
				}
				if(hasWon) {
					return player;
				}
			}
		}
		return null;
	}

	public Player getWinnerInMajorDiagonal() {
		ArrayList<Player> playersInMajorDiagonal = new ArrayList<>();
		Player playerInFirstColAndRow = getCell(0, 0);
		for(int i = 0; i < SIZE; i++) {
			playersInMajorDiagonal.add(getCell(i,i));
		}
		//if only this player exists in the major diagonal
		return playersInMajorDiagonal.stream().allMatch(player -> player == playerInFirstColAndRow)? playerInFirstColAndRow : null;
	}

	public Player getWinnerInMinorDiagonal() {
		ArrayList<Player> playersInMinorDiagonal = new ArrayList<>();
		Player playerInLastColAndFirstRow = getCell(SIZE - 1, 0);
		int columnI = SIZE - 1;
		int rowI = 0;
		while(columnI >= 0 && rowI < SIZE) {
			playersInMinorDiagonal.add(getCell(columnI, rowI));
			columnI--;
			rowI++;
		}
		//if only this player exists in the major diagonal
		return playersInMinorDiagonal.stream().allMatch(player -> player == playerInLastColAndFirstRow)? playerInLastColAndFirstRow : null;
	}



	/**
	 * Returns {@code true} if there is a free space on the board. Otherwise,
	 * returns {@code false}.
	 *
	 * @return {@code true} if there is a free space on the board, {@code false}
	 *         otherwise.
	 */
	public boolean hasSpace() {
		boolean spaceAvailable = false;
		for(int iCol = 0; iCol < SIZE; iCol++) {
			for(int iRow = 0; iRow < SIZE; iRow++) {
				if(getCell(iCol, iRow) == null) {
					spaceAvailable = true;
				}
			}
		}
		return spaceAvailable;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(int iRow = 0; iRow < SIZE; iRow++) {
			for(int iColumn = 0; iColumn < SIZE; iColumn++ ) {
				Player player = getCell(iColumn, iRow);
				if(player == null) {
					sb.append("   .   ");
				} else {
					sb.append("   " + player.getInitials() + "   ");
				}

			}
			sb.append("\n");
		}
		// for (every row) {
		//   for (every column) {
		//     get cell
		//     sb.append('.' if empty, otherwise initial of player);
		//   }
		//   append line separator
		// }
		
		return sb.toString();
	}

}
