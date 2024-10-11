import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class signUpPage implements ActionListener{
	JFrame frame = new JFrame();
	JLabel ageLabel = new JLabel("Age");
	JLabel email = new JLabel("Email");
	JLabel name = new JLabel("Name");
	JLabel surname = new JLabel("Surname");
	JButton button = new JButton("Sign Up");
	JButton reset = new JButton("Reset");
	JTextField userId = new JTextField();
	JTextField ageField = new JTextField();
	JTextField emailField = new JTextField();
	JTextField nameField = new JTextField();
	JTextField surnameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("Username");
	JLabel userPasswordLabel = new JLabel("Password");
	JLabel messageLabel = new JLabel();
	signUpPage(){
		userIDLabel.setBounds(50,50,75,25);
		userPasswordLabel.setBounds(50,80,75,25);
		ageLabel.setBounds(50,110,75,25);
		email.setBounds(50,140,75,25);
		name.setBounds(50,170,75,25);
		surname.setBounds(50,200,75,25);

		messageLabel.setBounds(125,300,250,35);
		messageLabel.setFont(new Font(null, Font.ITALIC,15));
		
		
		userId.setBounds(125,50,200,25);
		passwordField.setBounds(125,80,200,25);
		ageField.setBounds(125,110,200,25);
		emailField.setBounds(125,140,200,25);
		surnameField.setBounds(125,170,200,25);
		nameField.setBounds(125,200,200,25);
		
		button.setBounds(150,250,100,25);
		button.setFocusable(false);

		button.addActionListener(this);
		
		reset.setBounds(275,250,100,25);
		reset.setFocusable(false);
		reset.addActionListener(this);
		
		frame.add(button);
		frame.add(reset);
		
		frame.add(userId);
		frame.add(passwordField);
		frame.add(messageLabel);
		frame.add(ageLabel);
		frame.add(email);
		frame.add(name);
		frame.add(surname);
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(ageField);
		frame.add(emailField);
		frame.add(surnameField);
		frame.add(nameField);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public void addUser(String e, String username,String surname,String email,String password,String name,String age) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(e,true));
			String currentUser = "";
			currentUser += username + "," + password + "," + age + "," + email + "," + name + "," + surname + "\n";			writer.write(currentUser);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public boolean isValid(String password) {
		int uppercaseCounter = 0;
		int digitCounter = 0;
		int specialCounter = 0;
		for (int i=0; i < password.length(); i++ ) {
            char c = password.charAt(i);
            if(Character.isLetter(c)) 
                  uppercaseCounter++;
            else if(Character.isDigit(c)) 
                  digitCounter++;     
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
	public boolean isValidUser(String password) {
		int uppercaseCounter = 0;
		int digitCounter = 0;
		for (int i=0; i < password.length(); i++ ) {
            char c = password.charAt(i);
            if(!Character.isLetter(c)) 
                  uppercaseCounter++;
            else if(!Character.isDigit(c)) 
                  digitCounter++;     
		}
		if (uppercaseCounter*digitCounter == 0) {
			return true;
		} else {
			return false;
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
	public boolean usernameExists(String username, String e) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(e));
			String currentUser;
			String[] my_array;
			String line;
			while ((line = reader.readLine()) != null) {
				my_array = line.split(",");
				currentUser = my_array[0];
				if (currentUser.equals(username)) {
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
			nameField.setText("");
			emailField.setText("");
			surnameField.setText("");
			ageField.setText("");
		}
		if(e.getSource() == button) {
			
			String userIDField = userId.getText();
			String userPasswordField = String.valueOf(passwordField.getPassword());
			String namefield = nameField.getText();
			String emailfield = emailField.getText();
			String surnamefield = surnameField.getText();
			String agefield = ageField.getText();
			if (usernameExists(userIDField,"users.txt")) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("username taken");
			} else if (userPasswordField.length() < 8 || userPasswordField.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("password must be at least 8 characters.");
			} else if (isValid(userPasswordField) == false || userPasswordField.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("password must contain at least one digit and one special character.");
			} else if (isValidUser(userIDField) == false || userIDField.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("username must only contain letters and digits.");
			} else if (namefield.length() < 3 || namefield.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("your name must be at least 3 characters long.");
			} else if (Integer.parseInt(agefield) < 12) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("you must be over 12 to sign up.");
			} else if (surnamefield.length() < 3 || surnamefield.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("your surname must be at least 3 characters long.");
			} else if (isValidEmail(emailfield) == false || emailfield.isEmpty()) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("invalid email");
			}
			else {
				//Id_Passwords.addUser(userIDField, userPasswordField);
				addUser("users.txt",userIDField,surnamefield,emailfield,userPasswordField,namefield,agefield);
				frame.dispose();
				Login_Page new_login_page = new Login_Page(Id_Passwords.logininfo);
			}
		}
	}
}
