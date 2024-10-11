package PlayerStatsPackage;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	String[] playerStats;
	Position position;
	static int n = 5;
	
	double weightScore;
	double weightAssist;
	double weightSteal;
	double weightBlock;
	double weightRebound;
	public Player(String[] playerlist) {
		this.playerStats = playerlist;
		this.position = new Position(playerStats[1]);
		if (position.getposition() == "C") {
			weightScore = 0.3;
			weightAssist = 0.15;
			weightSteal = 0.05;
			weightBlock = 0.2;
			weightRebound = 0.3;
		} else if (position.getposition() == "PG") {
			weightScore = 0.5;
			weightAssist = 0.3;
			weightSteal = 0.1;
			weightBlock = 0.05;
			weightRebound = 0.05;
		} else if (position.getposition() == "SG") {
			weightScore = 0.7;
			weightAssist = 0.1;
			weightSteal = 0.1;
			weightBlock = 0.05;
			weightRebound = 0.05; 
		} else if (position.getposition() == "SF") {
			weightScore = 0.3;
			weightAssist = 0.2;
			weightSteal = 0.1;
			weightBlock = 0.1;
			weightRebound = 0.3;
		} else if (position.getposition() == "PF") {
			weightScore = 0.3;
			weightAssist = 0.2;
			weightSteal = 0.05;
			weightBlock = 0.2;
			weightRebound = 0.25;
		} else {
			weightScore = 0.4;
			weightAssist = 0.2;
			weightSteal = 0.05;
			weightBlock = 0.1;
			weightRebound = 0.25;
		}
	}
	public void displayPlayer() {
		for (String string : playerStats) {
			System.out.print(string + ", ");
		}
		System.out.print("\n");
	}
	public int calculateScore(String[] my_arraylist) {
		int score = 0;
		ArrayList<Double> arraylist2 = new ArrayList<Double>();
		arraylist2.add(weightScore);
		arraylist2.add(weightAssist);
		arraylist2.add(weightSteal);
		arraylist2.add(weightBlock);
		arraylist2.add(weightRebound);
		ArrayList<Double> arraylist = new ArrayList<Double>();
		Random random = new Random();
		int my_integer = random.nextInt(6);
		double number = Double.valueOf(my_arraylist[2]);
		arraylist.add(number);
		double numberAst = Double.valueOf(my_arraylist[3]);
		arraylist.add(numberAst);
		double numberStl = Double.valueOf(my_arraylist[4]);
		arraylist.add(numberStl);
		double numberBlk = Double.valueOf(my_arraylist[5]);
		arraylist.add(numberBlk);
		double numberReb = Double.valueOf(my_arraylist[6]);
		arraylist.add(numberReb);
		int sum = 0;
		for (double i : arraylist) {
			if (i-my_integer > 0) {
				double range = random.nextDouble(number-my_integer, my_integer+number+1);
				score += (int) Math.round(range*arraylist2.get(sum));
				sum += 1;
			} else {
				double range = random.nextDouble(0, my_integer+number+1);
				score += (int) Math.round(range*arraylist2.get(sum));
				sum += 1;
			}
		}
		return score;
	}
	public String returnPlayerName() {
		return playerStats[0];
	}
	public Position getPosition() {
		return position;
	}
	public String[] getStats() {
		return playerStats;
	}
}
