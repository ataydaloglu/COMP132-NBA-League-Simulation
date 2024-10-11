package SeasonGames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import PlayerStatsPackage.Team;

public class playOffs {
	ArrayList<Team> playoffs;
	public playOffs(ArrayList<Team> playoffs){
		this.playoffs = playoffs;
	}
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
		}
	}
	public Team playGame(ArrayList<Team> my_arraylist,String e) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(e,true));
			Team team = my_arraylist.get(0);
			Team team2 = my_arraylist.get(1);
			writer.write(team.getName() + " vs " + team2.getName() + "\n");
			//System.out.println(team.getName() + " vs " + team2.getName());
			if (team.getTotalScoreTeam() > team2.getTotalScoreTeam()) {
				writer.write(team.getName() + " wins" + "\n");
				//System.out.println(team.getName() + " wins");
				writer.close();
				sleep(100);
				return team;
			} else if (team.getTotalScoreTeam() < team2.getTotalScoreTeam()) {
				writer.write(team2.getName() + " wins" + "\n");
				writer.close();
				//System.out.println(team2.getName() + " wins");
				sleep(100);
				
				return team2;
			} else {
				//System.out.println("It's a tie");
				sleep(100);
				return playGame(my_arraylist,e);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return my_arraylist.get(0);
		}
	}
	public Team findChamp(ArrayList<Team> matchups, String e) {
		BufferedWriter writer2;
		try {
			writer2 = new BufferedWriter(new FileWriter(e));
			writer2.write("");
			writer2.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<ArrayList<Team>> object1 = distrubuteTeams8(matchups);
		ArrayList<Team> object2 = playoffs12(object1,e);
		ArrayList<ArrayList<Team>> object3 = distrubuteTeams4(object2);
		ArrayList<Team> matcher = playoffs12(object3,e);
		Team champion = playGame(matcher,e);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(e,true));
			writer.write(champion.getName() + " is the NBA Champion.");
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Team champion = playGame(games.get(0));
		return champion;
		
	}
	public ArrayList<ArrayList<Team>> distrubuteTeams4(ArrayList<Team> arraylist) {
		ArrayList<Team> matcher = new ArrayList<Team>();
		ArrayList<Team> matcher2 = new ArrayList<Team>();
		ArrayList<ArrayList<Team>> upper = new ArrayList<ArrayList<Team>>();
		matcher.add(arraylist.get(0));
		matcher.add(arraylist.get(1));
		matcher2.add(arraylist.get(2));
		matcher2.add(arraylist.get(3));
		upper.add(matcher);
		upper.add(matcher2);
		//System.out.println(upper.toString());
		return upper;
	}
	public ArrayList<Team> playoffs12(ArrayList<ArrayList<Team>> teams,String e){
		ArrayList<Team> matcher = new ArrayList<Team>();
		for (ArrayList<Team> matches2 : teams) {
			matcher.add(playGame(matches2,e));
		}
		return matcher;
	}
	
	
	
	
	public ArrayList<ArrayList<Team>> distrubuteTeams8(ArrayList<Team> arraylist) {
		ArrayList<Team> matcher = new ArrayList<Team>();
		ArrayList<Team> matcher2 = new ArrayList<Team>();
		ArrayList<Team> matcher3 = new ArrayList<Team>();
		ArrayList<Team> matcher4 = new ArrayList<Team>();
		ArrayList<ArrayList<Team>> upper = new ArrayList<ArrayList<Team>>();
		matcher.add(arraylist.get(0));
		matcher.add(arraylist.get(1));
		matcher2.add(arraylist.get(2));
		matcher2.add(arraylist.get(3));
		matcher3.add(arraylist.get(4));
		matcher3.add(arraylist.get(5));
		matcher4.add(arraylist.get(6));
		matcher4.add(arraylist.get(7));
		upper.add(matcher);
		upper.add(matcher2);
		upper.add(matcher3);
		upper.add(matcher4);
		//System.out.println(upper.toString());
		return upper;
	}
}
