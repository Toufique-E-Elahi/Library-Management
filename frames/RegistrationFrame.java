package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import repository.*;
import entity.*;

public class RegistrationFrame extends JFrame implements ActionListener
{
	private JButton submitBtn, backBtn;
	private JLabel nameLabel,idLabel,emailLabel,addressLabel;
	private JTextField nameTF,idTF,emailTF,addressTF;
	
	private JPanel panel;
	private LoginFrame lf;
	private CustomerRepo cr;
	private UserRepo ur;
	
	public RegistrationFrame(LoginFrame lf)
	{
		super("Register Now !!!");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.lf = lf;
		
		cr = new CustomerRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);


		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(200, 70, 80, 30);
		panel.add(nameLabel);
		
		idLabel = new JLabel(" ID : ");
		idLabel.setBounds(200, 170, 80, 30);
		panel.add(idLabel);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setBounds(200,120, 80, 30);
		panel.add(emailLabel);
		
		addressLabel = new JLabel("Address: ");
		addressLabel.setBounds(200, 220, 70, 30);
		panel.add(addressLabel);
		
		
		addressTF = new JTextField();
		addressTF.setBounds(320, 220, 100, 30);
		panel.add(addressTF);
		

		nameTF=new JTextField();
		nameTF.setBounds(320,70,100,30);
		panel.add(nameTF);

		idTF=new JTextField();
		idTF.setBounds(320,170,100,30);
		panel.add(idTF);

		emailTF=new JTextField();
		emailTF.setBounds(320,120,100,30);
		panel.add(emailTF);


		submitBtn = new JButton("Submit");
		submitBtn.setBounds(300, 300, 80, 30);
		submitBtn.addActionListener(this);
		panel.add(submitBtn);
		
		
		backBtn = new JButton("Back");
		backBtn.setBounds(390, 300, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(submitBtn.getText()))
		{
			//write codes for Customer Insertion Here
			Customer c=new Customer();
			User u=new User();
			Random rd = new Random();
			int x = rd.nextInt(9999999)+10000000;
			c.setCstId(idTF.getText());
			c.setName(nameTF.getText());
			c.setAddress(addressTF.getText());
			c.setEmail(emailTF.getText());
			
			u.setUserId(idTF.getText());
			u.setPassword(x+"");
			u.setStatus(2);
			
			cr.insertInDB(c);
			ur.insertUser(u);
			
			JOptionPane.showMessageDialog(this, "Inserted, Id: "+idTF.getText()+"and Password: "+x);
			this.setVisible(false);
			lf.setVisible(true);
		}
		else if(command.equals(backBtn.getText()))
		{
			this.setVisible(false);
			lf.setVisible(true);
		}
		else{}
	}
}


