import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PlayerStatsPackage.Team;
import readingCVSPackage.ReadingCVS;

public class Welcome_Page implements ActionListener{
	Team myTeam;
	String userID;
	JFrame frame = new JFrame();
	JLabel welcomepage = new JLabel("Hello");
	JButton button = new JButton("Log Out");
	JButton reset_password = new JButton("Change Info");
	JButton play = new JButton("Play");

	
	Welcome_Page(String userIDField){
		this.userID = userIDField;
		myTeam = new Team(userID);
		welcomepage.setBounds(100,100,300,35);
		welcomepage.setFont(new Font(null,Font.BOLD,35));
		welcomepage.setText("Hello " + userIDField);
		
		
		frame.add(welcomepage);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(420,420);
		frame.setLayout(null);
		
		play.setBounds(125,250,150,30);
		play.setFocusable(false);
		play.addActionListener(this);
		frame.add(play);
		
		button.setBounds(50,200,150,30);
		button.setFocusable(false);

		button.addActionListener(this);
		frame.add(button);
		
		reset_password.setBounds(200,200,150,30);
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
			Login_Page loginpage = new Login_Page(Id_Passwords.logininfo);
		}
		if (e.getSource() == reset_password) {
			frame.dispose();
			New_Password new_password = new New_Password(userID);
		}
		if (e.getSource() == play) {
			frame.dispose();
			ReadingCVS reading = new ReadingCVS();
			ArrayList<String> players = new ArrayList<String>();
			ArrayList<String[]> allPlayers = reading.ReadNReturn("C:\\Users\\DELL\\eclipse-workspace\\StartExample\\src\\2022-2023 NBA Player Stats - Regular.csv");
			for (String[] string : allPlayers) {
				players.add(string[0] + " , " + string[1]);
			}
			DraftPlayer playsim = new DraftPlayer(userID,myTeam,allPlayers,players);
		}
	}
}
