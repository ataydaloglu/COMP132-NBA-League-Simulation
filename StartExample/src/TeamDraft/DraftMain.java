package TeamDraft;

import java.util.ArrayList;

import PlayerStatsPackage.Player;
import PlayerStatsPackage.Team;
import readingCVSPackage.ReadingCVS;

public class DraftMain {
	ArrayList<Team> teams;
	public DraftMain(ArrayList<Team> teams){
		this.teams = teams;
		teams.add(new Team("Bulls"));
		teams.add(new Team("Nuggets"));
		teams.add(new Team("Bucks"));
		teams.add(new Team("Lakers"));
		teams.add(new Team("Jazz"));
		teams.add(new Team("Suns"));
		teams.add(new Team("Bobcats"));
		teams.add(new Team("Sonics"));
		teams.add(new Team("Hawks"));
		teams.add(new Team("Wizards"));
		teams.add(new Team("Grizzlies"));
		teams.add(new Team("Pistons"));
		teams.add(new Team("Cavaliers"));
		teams.add(new Team("Warriors"));
		teams.add(new Team("Nets"));


	}
	public ArrayList<Team> AutoDraft() {
		ArrayList<Team> teamlist = new ArrayList<Team>();
		ArrayList<Player> playerlist = new ArrayList<Player>();
		ReadingCVS reader = new ReadingCVS();
		ArrayList<String[]> my_arraylist = reader.ReadNReturn("C:\\Users\\DELL\\eclipse-workspace\\StartExample\\src\\2022-2023 NBA Player Stats - Regular.csv");
		for (String[] arrays : my_arraylist) {
			Player player = new Player(arrays);
			playerlist.add(player);
		}
		SimulationDraft simdraft = new SimulationDraft(teams);
		for (int i = 0; i <5; i++) {
			for (Team team : teams) {
				playerlist.remove(simdraft.randomlyAdd(team,playerlist));
			}		
		}
		return simdraft.teams;
	}
	public void addTeam(Team team) {
		teams.add(team);
	}
	public ArrayList<Team> getTeams(){
		return teams;
	}
	
}
