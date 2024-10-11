import java.awt.Color;
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
import readingCVSPackage.ReadingCVS;

public class DraftPlayer implements ActionListener{
	ArrayList<String> stringlist;
	ArrayList<String[]> mylist;
	JComboBox comboBox;
	ArrayList<String[]> allPlayers;
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel("Hello");
	JLabel message = new JLabel();
	public DraftPlayer(String userID, Team team, ArrayList<String[]> mylist,ArrayList<String> stringlist){
		this.stringlist = stringlist;
		this.mylist = mylist;
		comboBox = new JComboBox(stringlist.toArray());
		this.userID = userID;
		this.myTeam = team;
		
		
		message.setForeground(Color.GREEN);		
		message.setBounds(100,100,200,25);
		frame.add(message);
		
		welcomepage.setBounds(100,30,500,30);
		welcomepage.setFont(new Font(null,Font.PLAIN,25));
		welcomepage.setText("Drafting as: " + userID);
		
		comboBox.addActionListener(this);
		
		comboBox.setBounds(100,180,200,35);
		frame.add(comboBox);
		
		frame.add(welcomepage);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(420,420);
		frame.setLayout(null);
		
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == comboBox) {
			String my_string = comboBox.getSelectedItem().toString();
			String[] string = my_string.split(" , ");
			int size = mylist.size();
			for (int i = 0; i < size-1; i++) {
				if (mylist.get(i)[0].equals(string[0])) {
					Player my_player = new Player(mylist.get(i));
					myTeam.addPlayer(my_player);
					message.setForeground(Color.GREEN);
					message.setText("Player added to your team");
					mylist.remove(mylist.get(i));
					stringlist.remove(my_string);
					if (myTeam.returnLenght() < 5) {
						frame.dispose();
						DraftPlayer newplayer = new DraftPlayer(userID,myTeam,mylist,stringlist);
					}
					if (myTeam.returnLenght() == 5) {
						frame.dispose();
						DraftDoneCheckTeam startsim = new DraftDoneCheckTeam(userID,myTeam);
//						ArrayList<Team> teams = new ArrayList<Team>();
//						DraftMain draftsim = new DraftMain(teams);
//						ArrayList<Team> another22 = draftsim.AutoDraft();
//						another22.add(myTeam);
//						for (Team allTeams : another22) {
//							allTeams.displayTeam();
//							System.out.println(allTeams.getTotalScoreTeam());
//							System.out.print("\n");
//						}
//						playSeason normalseason = new playSeason(another22);
//						ArrayList<Team> qualified = normalseason.playGame(another22);
//						playOffs playoffs12 = new playOffs(qualified);
//						ArrayList<ArrayList<Team>> eşleşme = playoffs12.distrubuteTeams(qualified);
//						Team winner = playoffs12.findChamp(eşleşme);
//						System.out.println(winner.getName() + " is the NBA champion");
//						frame.dispose();
					}
				}
			}
		}
	}
}
	
