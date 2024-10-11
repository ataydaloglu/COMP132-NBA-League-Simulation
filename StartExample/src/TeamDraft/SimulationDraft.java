package TeamDraft;

import java.util.ArrayList;
import java.util.Random;

import PlayerStatsPackage.Player;
import PlayerStatsPackage.Team;

public class SimulationDraft {
	ArrayList<Team> teams;
	SimulationDraft(ArrayList<Team> teams){
		this.teams = teams;
	}
	public Player randomlyAdd(Team team, ArrayList<Player> players) {
		Random random = new Random();
		int randomint = random.nextInt(1,players.size());
		team.addPlayer(players.get(randomint));
		return players.get(randomint);
	}
	public 	ArrayList<Team> getTeams(){
		return teams;
	}

}
