import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_Page implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton button = new JButton("Login");
	JButton reset = new JButton("Reset");
	JButton signup = new JButton("Sign Up");
	JTextField userId = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("User ID");
	JLabel userPasswordLabel = new JLabel("Password");
	JLabel messageLabel = new JLabel();
	JLabel welcomeLabel = new JLabel();
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	Login_Page(HashMap<String,String> loginInfoOriginal){
		logininfo = loginInfoOriginal;
		
		userIDLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);

		welcomeLabel.setBounds(25,50,450,25);
		welcomeLabel.setFont(new Font(null, Font.PLAIN,25));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setText("Welcome to NBA Draft Simulation");
		
		messageLabel.setBounds(125,300,250,35);
		messageLabel.setFont(new Font(null, Font.ITALIC,25));
		
		userId.setBounds(125,100,200,25);
		passwordField.setBounds(125,150,200,25);
		
		signup.setBounds(175,250,100,25);
		signup.setFocusable(false);
		signup.addActionListener(this);
		
		button.setBounds(125,200,100,25);
		button.setFocusable(false);

		button.addActionListener(this);
		
		reset.setBounds(225,200,100,25);
		reset.setFocusable(false);
		reset.addActionListener(this);
		
		frame.add(button);
		frame.add(reset);
		frame.add(signup);
		
		frame.add(userId);
		frame.add(passwordField);
		frame.add(messageLabel);
		frame.add(welcomeLabel);
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public boolean findUser(String e, String user, String password) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(e));
			String currentUser;
			String[] my_array;
			String line;
			while ((line = reader.readLine()) != null) {
				my_array = line.split(",");
				String username = (String) user.strip();
				String my_password = (String) password.strip();
				String checkinguser = (String) my_array[0].strip();
				String checkingpassword = (String) my_array[1].strip();
				//System.out.println(username.equals(checkinguser));
				//System.out.println(my_password.equals(checkingpassword));
				if (username.equals(checkinguser) && my_password.equals(checkingpassword)) {
					return true;
				}
			}
			reader.close();
			return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
			
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == reset) {
			userId.setText("");
			passwordField.setText("");
		}
		if(e.getSource() == button) {
			
			String userIDField = userId.getText();
			String userPasswordField = String.valueOf(passwordField.getPassword());

			
			if (findUser("users.txt",userIDField,userPasswordField)) {
				frame.dispose();
				Welcome_Page welcomepage = new Welcome_Page(userIDField);
			} else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Cannot Login");
			}
//			if (logininfo.containsKey(userIDField)) {
//				if(logininfo.get(userIDField).equals(userPasswordField)) {
//					messageLabel.setForeground(Color.green);
//					messageLabel.setText("Login Successful");
//					frame.dispose();
//					Welcome_Page welcomepage = new Welcome_Page(userIDField);
//				} else {
//					messageLabel.setForeground(Color.red);
//					messageLabel.setText("Wrong Password");
//				}
//			} else {
//				messageLabel.setForeground(Color.red);
//				messageLabel.setText("username not found");
//			}
		}
		if (e.getSource() == signup) {
			frame.dispose();
			signUpPage signuppage = new signUpPage();
		}
	}
}
