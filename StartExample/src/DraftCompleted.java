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

public class DraftCompleted implements ActionListener{
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel("Hello");
	JButton button = new JButton("Display Team");
	JButton play = new JButton("Start Simulation");

	
	DraftCompleted(String userIDField, Team my_team){
		this.userID = userIDField;
		this.myTeam = my_team;
		welcomepage.setBounds(0,0,200,35);
		welcomepage.setFont(new Font(null,Font.PLAIN,25));
		welcomepage.setText("Team " + userIDField);
		
		
		frame.add(welcomepage);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(420,420);
		frame.setLayout(null);
		
		play.setBounds(125,250,100,25);
		play.setFocusable(false);
		play.addActionListener(this);
		frame.add(play);
		
		button.setBounds(125,200,100,25);
		button.setFocusable(false);

		button.addActionListener(this);
		frame.add(button);
		
		
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			myTeam.displayTeam();
		}
		if (e.getSource() == play) {
			ArrayList<Team> teams = new ArrayList<Team>();
			DraftMain draftsim = new DraftMain(teams);
			ArrayList<Team> another22 = draftsim.AutoDraft();
			another22.add(myTeam);
			for (Team allTeams : another22) {
				allTeams.displayTeam();
				System.out.println(allTeams.getTotalScoreTeam());
				System.out.print("\n");
			}
			playSeason normalseason = new playSeason(another22);
			ArrayList<Team> qualified = normalseason.playGame(another22,"seasonGames.txt","seasonStats.txt");
			for (Team team : qualified) {
				if (team.equals(myTeam)) {
					qualifiedPlayoffs object = new qualifiedPlayoffs(userID,myTeam,qualified,"Congrats");
				}
			}
			if (!qualified.contains(myTeam)) {
				notQualified object2 = new notQualified(userID,myTeam,"Nice Try");
				playOffs playoffs12 = new playOffs(qualified);
				Team winner = playoffs12.findChamp(qualified,"playoffStats.txt");
				System.out.println(winner.getName() + " is the NBA champion");
			}
			frame.dispose();
		}
	}
}
