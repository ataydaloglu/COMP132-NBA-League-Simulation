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

public class DraftDoneCheckTeam implements ActionListener{
	ArrayList<Team> teams;
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel("Team " + userID);
	JLabel congrats = new JLabel();
	JButton button = new JButton("Display Team");
	JButton play = new JButton("Continue with the simulation");

	
	public DraftDoneCheckTeam(String userIDField, Team my_team){
		this.userID = userIDField;
		this.myTeam = my_team;
		congrats.setBounds(100,150,200,25);
		congrats.setFont(new Font(null,Font.PLAIN,20));
		congrats.setText("Drafting complete.");
		frame.add(congrats);
		
		welcomepage.setBounds(0,0,200,35);
		welcomepage.setFont(new Font(null,Font.PLAIN,25));
		welcomepage.setText("Team " + userID);
		
		ArrayList<String> players = new ArrayList<String>();
		
		for (Player player: my_team.getPlayerlist()) {
			players.add(player.returnPlayerName());
		}
		
		frame.add(welcomepage);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(420,420);
		frame.setLayout(null);
		
		play.setBounds(100,250,200,25);
		play.setFocusable(false);
		play.addActionListener(this);
		frame.add(play);
		
		button.setBounds(125,200,150,25);
		button.setFocusable(false);

		button.addActionListener(this);
		frame.add(button);
		
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			displayTeam display = new displayTeam(userID, myTeam);
			frame.dispose();
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
				System.out.println(winner.getName() + " is the NBA champion");
			}
			frame.dispose();
			
		}
	}
}
