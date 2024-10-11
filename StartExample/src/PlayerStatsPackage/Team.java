package PlayerStatsPackage;

import java.util.ArrayList;

public class Team {
	ArrayList<Player> playerlist;
	int totalScoreTeam;
	String name;
	int wins = 0;
	int losses = 0;
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public Team (String name) {
		playerlist = new ArrayList<Player>();
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public int getWins() {
		return wins;
	}
	public void teamWon() {
		this.wins += 1;
	}
	public void addPlayer(Player player) {
		playerlist.add(player);
	}
	public void displayTeam() {
		System.out.println(name);
		for (Player player: playerlist) {
			player.displayPlayer();
		}
	}
	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}
	public void setPlayerlist(ArrayList<Player> playerlist) {
		this.playerlist = playerlist;
	}
	public int totalScore() {
		int total = 0;
		int indvScore;
		for (Player player: playerlist) {
			indvScore = player.calculateScore(player.playerStats);
			total += indvScore;
		}
		return total;
	}
	public int getTotalScoreTeam() {
		this.totalScoreTeam = totalScore();
		return totalScoreTeam;
	}
	public int returnLenght() {
		return playerlist.size();
	}
	public String returnPlayers() {
		String my_string = name + "\n";
		for (Player player: playerlist) {
			my_string += player.returnPlayerName() + "\n";
		}
		return my_string;
	}
}
