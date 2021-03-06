package uk.ac.aston.jpd.tut1.tictactoe.game;

/**
 * Represents a player in the Tic-Tac-Toe game.
 * 
 * @author Lewis
 *
 */
public class Player {
	private final String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public String getInitials() {
		String[] splitName = name.split(" ");
		StringBuilder initials = new StringBuilder();
		for(String name: splitName) {
			initials.append(name.charAt(0));
		}
		return initials.toString();
	}

	@Override
	public String toString() {
		return "Player with name: " + this.name;
	}
	
	
}
