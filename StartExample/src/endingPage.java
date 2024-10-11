import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PlayerStatsPackage.Team;
import readingCVSPackage.ReadingCVS;

public class endingPage implements ActionListener{
	ArrayList<Team> teams;
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel(userID);
	JLabel congrats = new JLabel();
	JLabel wins = new JLabel();
	JLabel wins2 = new JLabel();
	JLabel losses = new JLabel();
	JLabel champ1 = new JLabel();
	JButton button = new JButton("Restart Simulation");
	JButton reset_password = new JButton("Exit");

	
	public endingPage(String userIDField, Team my_team, Team champ){
		this.userID = userIDField;
		this.myTeam = my_team;
		
		welcomepage.setBounds(0,0,200,35);
		welcomepage.setFont(new Font(null,Font.PLAIN,25));
		welcomepage.setText("Team " + userID);
		
		wins.setBounds(50,50,300,30);
		wins.setFont(new Font(null,Font.PLAIN,25));
		wins.setText(myTeam.getName() + " ended with: ");
		
		wins2.setBounds(200,80,300,30);
		wins2.setFont(new Font(null,Font.PLAIN,25));
		wins2.setText(String.valueOf(myTeam.getWins()) + " wins");
		
		losses.setBounds(200,110,300,30);
		losses.setFont(new Font(null,Font.PLAIN,25));
		losses.setText(String.valueOf(myTeam.getLosses()) + " losses");
		
		if (champ.equals(my_team)) {
			champ1.setBounds(30,140,400,30);
			champ1.setFont(new Font(null,Font.PLAIN,25));
			champ1.setText(my_team.getName() + " is the NBA Champion");
		} else {
			champ1.setBounds(30,140,400,30);
			champ1.setFont(new Font(null,Font.PLAIN,25));
			champ1.setText(champ.getName() + " is the NBA Champion");
		}
		frame.add(champ1);
		
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
			frame.dispose();
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
			frame.dispose();
		}
	}
}
