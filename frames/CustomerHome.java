package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class CustomerHome extends JFrame implements ActionListener
{
	JButton logoutBtn, searchBookBtn, updateBtn, changePasswordBtn,searchLoanBtn;
	JPanel panel;
	
	User user;
	
	public CustomerHome(User user)
	{
		super("Welcome Customer");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 150, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		changePasswordBtn = new JButton("Change Password");
		changePasswordBtn.setBounds(600, 100, 150, 30);
		changePasswordBtn.addActionListener(this);
		panel.add(changePasswordBtn);
		
		searchBookBtn = new JButton("Search Book");
		searchBookBtn.setBounds(50, 100, 150, 30);
		searchBookBtn.addActionListener(this);
		panel.add(searchBookBtn);
		
		updateBtn = new JButton("Update Profile");
		updateBtn.setBounds(225, 100, 150, 30);
		updateBtn.addActionListener(this);
		panel.add(updateBtn);
		
		
		searchLoanBtn = new JButton("Loan Info");
		searchLoanBtn.setBounds(400, 100, 150, 30);
		searchLoanBtn.addActionListener(this);
		panel.add(searchLoanBtn);
		
		this.add(panel);
	
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			
				CustomerFrame cf = new CustomerFrame(user);
				cf.setVisible(true);
				this.setVisible(false);
			
		}
		else if(command.equals(changePasswordBtn.getText())) 
		{
			PasswordFrame pf= new PasswordFrame(user, this);
			pf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(searchBookBtn.getText()))
		{
			SearchBookFrame sb = new SearchBookFrame(user);
			sb.setVisible(true);
			this.setVisible(false);
			
		}
		else if(command.equals(searchLoanBtn.getText()))
		{
			SearchLoanFrame sf = new SearchLoanFrame(user);
			sf.setVisible(true);
			this.setVisible(false);

		}
		else{}
	}
}