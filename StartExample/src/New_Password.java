import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class New_Password implements ActionListener{
	JFrame frame = new JFrame();
	JLabel ageLabel = new JLabel("Age");
	JLabel email = new JLabel("Email");
	JButton button = new JButton("Change Info");
	JButton reset = new JButton("Reset");
	JTextField ageField = new JTextField();
	JTextField emailField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel userPasswordLabel = new JLabel("Password");
	JLabel messageLabel = new JLabel();
	String userId;
	New_Password(String userID2){
		this.userId = userID2;
		userPasswordLabel.setBounds(50,80,75,25);
		ageLabel.setBounds(50,110,75,25);
		email.setBounds(50,140,75,25);
		
		passwordField.setBounds(125,80,200,25);
		ageField.setBounds(125,110,120,25);
		emailField.setBounds(125,140,200,25);

		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null, Font.ITALIC,25));
		
		button.setBounds(150,200,150,25);
		button.setFocusable(false);

		button.addActionListener(this);
		
		reset.setBounds(150,250,150,25);
		reset.setFocusable(false);
		reset.addActionListener(this);
		
		frame.add(passwordField);
		frame.add(messageLabel);
		frame.add(ageLabel);
		frame.add(email);
		frame.add(button);
		frame.add(reset);
		
		frame.add(userPasswordLabel);
		frame.add(ageField);
		frame.add(emailField);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public void changeInfo(String userId,String e,String email,String password,String age){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(e));
			String currentUser = "";
			String[] my_array;
			String line;
			while ((line = reader.readLine()) != null) {
				my_array = line.split(",");
				if (my_array[0].equals(userId)) {
					currentUser += userId + "," + password + "," + age + "," + email + "," + my_array[4] + "," + my_array[5] + "\n";
				} else {
				currentUser += my_array[0] + "," + my_array[1] + "," + my_array[2] + "," + my_array[3] + "," + my_array[4] + "," + my_array[5] + "\n";
				}
			}
			reader.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(e));
			writer.write(currentUser);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public boolean isValidEmail(String email) {
		String regex = "^(.+)@(.+)$";  
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        //Matcher matcher2 = pattern.matcher("eadaloglu@gmail.com");
        //System.out.println(matcher2.matches());
        return matcher.matches();
	}
	public boolean isValid(String password) {
		int uppercaseCounter = 0;
		int digitCounter = 0;
		int specialCounter = 0;
		for (int i=0; i < password.length(); i++ ) {
            char c = password.charAt(i);
            if(Character.isLetter(c)) {
            	uppercaseCounter++;
            }
            else if(Character.isDigit(c)) {
            	digitCounter++;     
            }
            else if(c == '$' || c == '#' || c == '?' || c == '!' || c == '_' || c == '=' || c == '%'){
              specialCounter++;
             }
		}
        if (uppercaseCounter*digitCounter*specialCounter == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			String userPasswordField = String.valueOf(passwordField.getPassword());
			String emailfield = emailField.getText();
			String agefield = ageField.getText();
			if (isValid(userPasswordField) == true) {
				changeInfo(userId,"users.txt",emailfield,userPasswordField,agefield);
				frame.dispose();
				Welcome_Page welcome = new Welcome_Page(userId);
			} else if (userPasswordField.length() < 8 || userPasswordField.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("password must be at least 8 characters.");
			} else if (isValid(userPasswordField) == false || userPasswordField.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("password must contain at least one digit and one special character.");
			} else if (Integer.parseInt(agefield) < 12) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("you must be over 12 to sign up.");
			} else if (isValidEmail(emailfield) == false || emailfield.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("invalid email");
			}
		}
		if (e.getSource() == reset) {
			passwordField.setText("");
		}
	}
}
