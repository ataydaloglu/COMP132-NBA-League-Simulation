import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PlayerStatsPackage.Team;
import SeasonGames.playOffs;
import readingCVSPackage.ReadingCVS;

public class notQualified implements ActionListener{
	String e;
	ArrayList<Team> teams;
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel(e + " " + userID);
	JLabel congrats = new JLabel();
	JLabel wins = new JLabel();
	JLabel wins2 = new JLabel();
	JLabel losses = new JLabel();
	JButton button = new JButton("Restart Simulation");
	JButton reset_password = new JButton("Exit");

	
	public notQualified(String userIDField, Team my_team, String e){
		this.e = e;
		this.userID = userIDField;
		this.myTeam = my_team;
		
		welcomepage.setBounds(0,0,200,35);
		welcomepage.setFont(new Font(null,Font.PLAIN,25));
		welcomepage.setText(e + " " + userID);
		
		wins.setBounds(50,50,300,30);
		wins.setFont(new Font(null,Font.PLAIN,25));
		wins.setText(myTeam.getName() + " ended with: ");
		
		wins2.setBounds(200,80,300,30);
		wins2.setFont(new Font(null,Font.PLAIN,25));
		wins2.setText(String.valueOf(myTeam.getWins()) + " wins");
		
		losses.setBounds(200,110,300,30);
		losses.setFont(new Font(null,Font.PLAIN,25));
		losses.setText(String.valueOf(myTeam.getLosses()) + " losses");
		
		frame.add(welcomepage);
		frame.add(wins);
		frame.add(wins2);
		frame.add(losses);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(420,420);
		frame.setLayout(null);
		
		
		button.setBounds(50,200,150,25);
		button.setFocusable(false);

		button.addActionListener(this);
		frame.add(button);
		
		reset_password.setBounds(200,200,150,25);
		reset_password.setFocusable(false);

		reset_password.addActionListener(this);
		frame.add(reset_password);
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			ReadingCVS reading = new ReadingCVS();
			ArrayList<String> players = new ArrayList<String>();
			ArrayList<String[]> allPlayers = reading.ReadNReturn("C:\\Users\\DELL\\eclipse-workspace\\StartExample\\src\\2022-2023 NBA Player Stats - Regular.csv");
			for (String[] string : allPlayers) {
				players.add(string[0] + " , " + string[1]);
			}
			DraftPlayer playsim = new DraftPlayer(userID,myTeam,allPlayers,players);
		}
		if (e.getSource() == reset_password) {
			HashMap<String,String> idPasswords = new HashMap<String,String>();
			
			Login_Page loginPage = new Login_Page(idPasswords);
		}
	}
}
