import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PlayerStatsPackage.Player;
import PlayerStatsPackage.Team;

public class showPlayer implements ActionListener{
	String userIDField;
	Team my_team;
	JFrame frame = new JFrame();
	Player player;
	JLabel stats = new JLabel();
	JButton back = new JButton("Back");
	showPlayer(Player player,String userIDField, Team my_team){
		this.player = player;
		this.userIDField = userIDField;
		this.my_team = my_team;
		
		back.setBounds(100,250,200,25);
		back.setFocusable(false);
		back.addActionListener(this);
		frame.add(back);
		
		stats.setBounds(10,50,400,30);
		stats.setFont(new Font(null,Font.PLAIN,25));
		stats.setText(player.getStats()[0] + " " + player.getStats()[1] + " " + player.getStats()[2]);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(420,420);
		frame.setLayout(null);
		
		frame.add(stats);
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			displayTeam returntodraft = new displayTeam(userIDField,my_team);
			frame.dispose();
		}
	}
}
