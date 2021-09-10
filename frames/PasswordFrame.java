package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class PasswordFrame extends JFrame implements ActionListener
{
	private JLabel userIdLabel, oldPassLabel,newPassLabel;
	private JPasswordField  oldPassTF, newPassTF;
	private JButton  changePassBtn, backBtn;
	private JPanel panel;
	
	private User user;
	private UserRepo ur;
	private JFrame prev;
	
	
	public PasswordFrame(User user, JFrame prev)
	{
		super("Password Frame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		this.prev = prev;
		
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		oldPassLabel = new JLabel("Old Password :");
		oldPassLabel.setBounds(300,150,100,30);
		panel.add(oldPassLabel);
		
		oldPassTF = new JPasswordField();
		oldPassTF.setBounds(420,150,100,30);
		oldPassTF.setEchoChar('*');
		panel.add(oldPassTF);
		
		newPassLabel = new JLabel("New Password: ");
		newPassLabel.setBounds(300,200,100,30);
		panel.add(newPassLabel);
		
		newPassTF = new JPasswordField();
		newPassTF.setBounds(420,200,100,30);
		newPassTF.setEchoChar('*');
		panel.add(newPassTF);
		
		changePassBtn = new JButton("Change");
		changePassBtn.setBounds(350,250,100,30);
		changePassBtn.addActionListener(this);
		panel.add(changePassBtn);
		
		backBtn = new JButton("Cancel");
		backBtn.setBounds(470, 250, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(changePassBtn.getText()))
		{
			if(!oldPassTF.getText().equals("") && !oldPassTF.getText().equals(null) ) 
			{
				if(oldPassTF.getText().equals(user.getPassword()))
				{
					if(!newPassTF.getText().equals("") && !newPassTF.getText().equals(null) ) 
					{
						user.setPassword(newPassTF.getText());
						ur.updateUser(user);
						JOptionPane.showMessageDialog(this,"Password Changed Successfully");
						this.setVisible(false);
						prev.setVisible(true);
					} 
					else
					{
						JOptionPane.showMessageDialog(this,"Please insert new password");
					}
				} 
				else
				{
					JOptionPane.showMessageDialog(this,"Old Password Doesnot Match");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Please insert old password");
			}
		}
		else if(command.equals(backBtn.getText()))
		{
			this.setVisible(false);
			prev.setVisible(true);
		}
		else{}
	}
}