import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PlayerStatsPackage.Team;
import SeasonGames.playOffs;
import SeasonGames.playSeason;
import TeamDraft.DraftMain;

public class qualifiedPlayoffs implements ActionListener{
	String ea;
	ArrayList<Team> teams;
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel(ea + " " + userID);
	JLabel congrats = new JLabel();
	JButton play = new JButton("Start Playoffs");

	
	public qualifiedPlayoffs(String userIDField, Team my_team, ArrayList<Team> teams, String ea){
		this.ea = ea;
		this.teams = teams;
		this.userID = userIDField;
		this.myTeam = my_team;
		congrats.setBounds(50,100,300,30);
		congrats.setFont(new Font(null,Font.PLAIN,20));
		congrats.setText("You have made it to the playoffs!");
		frame.add(congrats);
		
		welcomepage.setBounds(0,0,300,35);
		welcomepage.setFont(new Font(null,Font.PLAIN,25));
		welcomepage.setText(ea + " " + userID);
		
		
		frame.add(welcomepage);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(420,420);
		frame.setLayout(null);
		
		play.setBounds(100,250,200,25);
		play.setFocusable(false);
		play.addActionListener(this);
		frame.add(play);
		
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == play) {
			playOffs playoffs12 = new playOffs(teams);
			Team winner = playoffs12.findChamp(teams,"playoffStats.txt");
			endingPage ending = new endingPage(userID,myTeam,winner);
			//System.out.println(winner.getName() + " is the NBA champion");
			frame.dispose();
		}
	}
}
