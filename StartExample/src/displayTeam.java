import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PlayerStatsPackage.Player;
import PlayerStatsPackage.Team;
import SeasonGames.playOffs;
import SeasonGames.playSeason;
import TeamDraft.DraftMain;

public class displayTeam implements ActionListener{
	JComboBox comboBox;
	ArrayList<Team> teams;
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel("Team " + userID);
	JButton play = new JButton("Continue with the simulation");

	
	public displayTeam(String userIDField, Team my_team){
		this.userID = userIDField;
		this.myTeam = my_team;
		
		welcomepage.setBounds(0,0,400,35);
		welcomepage.setFont(new Font(null,Font.PLAIN,25));
		welcomepage.setText("Displaying team: " + userID);
		
		ArrayList<String> players = new ArrayList<String>();
		
		for (Player player: my_team.getPlayerlist()) {
			players.add(player.returnPlayerName());
		}
		
		comboBox = new JComboBox(players.toArray());

		comboBox.addActionListener(this);
		
		comboBox.setBounds(100,100,200,25);
		frame.add(comboBox);
		
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
		if (e.getSource() == comboBox) {
			for (Player player: myTeam.getPlayerlist()) {
				if (player.returnPlayerName().equals(comboBox.getSelectedItem())) {
					showPlayer showplayer = new showPlayer(player,userID,myTeam);
					frame.dispose();
				}
			}
		}
		if (e.getSource() == play) {
			ArrayList<Team> teams = new ArrayList<Team>();
			DraftMain draftsim = new DraftMain(teams);
			ArrayList<Team> another22 = draftsim.AutoDraft();
			another22.add(myTeam);
//			for (Team allTeams : another22) {
//				allTeams.displayTeam();
//				System.out.println(allTeams.getTotalScoreTeam());
//				System.out.print("\n");
//			}
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
				//System.out.println(winner.getName() + " is the NBA champion");
			}
			frame.dispose();
		}
	}
}
