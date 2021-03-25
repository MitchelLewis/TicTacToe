package uk.ac.aston.jpd.tut1.tictactoe;

import uk.ac.aston.jpd.tut1.tictactoe.game.Board;
import uk.ac.aston.jpd.tut1.tictactoe.game.Player;
import uk.ac.aston.jpd.tut1.tictactoe.io.TextUI;

import java.util.ArrayList;

/**
 * Represents the game flow for Tic-Tac-Toe.
 */
public class Game {
	private final Board board;
	private final ArrayList<Player> players;

	public Game(Board _board, Player _player1, Player _player2) {
		players = new ArrayList<Player>();
		players.add(_player1);
		players.add(_player2);
		board = _board;
	}

	/**
	 * Plays a round of the game, and returns the winner {@link Player} if there is one.
	 *
	 * @param ui Text-based user interface that should be used to present the game to the players.
	 */
	public Player playRound(TextUI ui) {
		boolean winnerFound = false;

		for(Player player: players) {
			ui.writeMessage(board.toString());
			ui.prompt("It is " + player.getName() + " turn. Enter a column and row.");
			int[] inputtedCell = ui.readNumbers(2);
			boolean successfulOccupation = board.occupyCell(inputtedCell[0], inputtedCell[1], player);
			if(!successfulOccupation) {
				ui.writeError("Cell was already occupied!");
			}
			Player winner = board.getWinner();
			if(winner != null) {
				return winner;
			}
		}

		return null;
		// for (each player) {
		//    show the board
		//    say whose turn it is
		//    ask for a column and a row
		//
		//    try to occupy the cell, report if it was occupied or not
		//
		//    if there is a winner, return it
		// }
		//
		// no winner yet
	}

	/**
	 * Returns {@code true} if the game has ended in a draw, {@code false} otherwise.
	 */
	public boolean isDraw() {
		// return false if there is at least one free space on the board
		return !board.hasSpace();
	}

	/**
	 * Entry point to the application.
	 */
	public static void main(String[] args) {
		// set up game and UI
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		Game game = new Game(new Board(), player1, player2);
		TextUI ui = new TextUI();

		Player winner = null;
		// keep playing rounds until there is a winner or the game ends in a draw
		while(winner == null && !game.isDraw()) {
			winner = game.playRound(ui);
		}

		if(winner != null) {
			ui.writeMessage(game.board.toString());
			ui.writeMessage("The winner is: " + winner.toString());
		} else {
			ui.writeMessage("It's a draw!");
		}
		// report winner
	}
}
