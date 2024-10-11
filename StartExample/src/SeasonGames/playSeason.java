package SeasonGames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import PlayerStatsPackage.Team;

public class playSeason {
	ArrayList<Team> teams;
	public playSeason(ArrayList<Team> teams){
		this.teams = teams;
	}
	public ArrayList<Team> playGame(ArrayList<Team> teams, String e,String b) {
		try {
			BufferedWriter writer3 = new BufferedWriter(new FileWriter(b));
			writer3.write("");
			writer3.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(e));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(b,true));
			ArrayList<Integer> wins = new ArrayList<Integer>();
			ArrayList<Team> playoffs = new ArrayList<Team>();
			for (Team team : teams) {
				int teamscore1 = team.getTotalScoreTeam();
				for (Team team2 : teams) {
					int teamscore2 = team2.getTotalScoreTeam();
					if (team != team2) {
						writer.write(team.getName() + " vs " + team2.getName() + "\n");
						//System.out.println(team.getName() + " vs " + team2.getName());
						if (teamscore1*1.05 > teamscore2) {
							//System.out.println(team.getName() + " wins");
							writer.write(team.getName() + " wins" + "\n");
							team.teamWon();
							team2.setLosses(team2.getLosses() + 1);
						} else if (teamscore1*1.05 < teamscore2) {
							//System.out.println(team2.getName() + " wins");
							writer.write(team2.getName() + " wins" + "\n");
							team2.teamWon();
							team.setLosses(team.getLosses() + 1);
						} else if (teamscore1*1.05 == teamscore2) {
							while (teamscore1*1.05 == teamscore2) {
								teamscore1 = team.getTotalScoreTeam();
								teamscore2 = team2.getTotalScoreTeam();
								if (teamscore1*1.05 > teamscore2) {
									//System.out.println(team.getName() + " wins");
									writer.write(team.getName() + " wins" + "\n");
									team.teamWon();
									team2.setLosses(team2.getLosses() + 1);
								} else if (teamscore1*1.05 < teamscore2) {
									//System.out.println(team2.getName() + " wins");
									writer.write(team2.getName() + " wins" + "\n");
									team2.teamWon();
									team.setLosses(team.getLosses() + 1);
								}
							}
						}
					}
				}
			}
			writer.close();
			for (Team teams22 : teams) {
				//System.out.println(teams22.getName() + ": " + teams22.getWins() + " wins, " + teams22.getLosses() + " losses.\n");
				writer2.write(teams22.getName() + ": " + teams22.getWins() + " wins, " + teams22.getLosses() + " losses.\n");
			}
			writer2.close();
			ArrayList<Team> sorted = teams;
			Collections.sort(sorted, new Comparator<Team>() {
				
				@Override
				public int compare(Team o1, Team o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1.getWins(),o2.getWins());
				}
				
			});
			for (int i = 8; i < 16 ; i++) {
				playoffs.add(sorted.get(i));
			}
			return playoffs;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return teams;
		}
		
		
//		for (Team team3 : teams) {
//			wins.add(team3.getWins());
//		}
//		Collections.sort(wins);
//		System.out.println(wins.toString());
//		ArrayList<Team> team_list = new ArrayList<Team>();
//		for (int i : wins) {
//			for (Team team3 : teams) {
//				if (team3.getWins() == i) {
//					team_list.add(team3);
//				}
//			}
//		}
//		for (Team team4 : teams) {
//			//System.out.println(team4.getName() + " has " + team4.getWins() + " number of wins");
//			if (team4.getWins() > wins.get(6)) {
//				System.out.println(team4.getName() + " has " + team4.getWins() + " which is bigger or equal to " + wins.get(6));
//				playoffs.add(team4);
//			} else if (team4.getWins() == wins.get(6)) {
//				System.out.println(team4.getName() + " has " + team4.getWins() + " which is bigger or equal to " + wins.get(6));
//				playoffs.add(team4);
//				wins.set(6, wins.get(6)+1);
//			}
//		}
		
		
	}
}
