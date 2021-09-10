package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class SearchLoanFrame extends JFrame implements ActionListener
{
	private JLabel loanIdLabel,bookIdLabel, cstIdLabel, returnDateLabel;
	private JTextField loanIdTF, bookIdTF, cstIdTF, returnDateTF;
	private JButton logoutBtn,loadBtn, refreshBtn, backBtn;
	private JPanel panel;
	private JTable listTable;
	private JScrollPane listTableSP;
	
	
	private User user;
	private LoanListRepo lr;
	private UserRepo ur;
	
	
	public SearchLoanFrame(User user)
	{
		super("Search Loan");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		lr = new LoanListRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Loan ID", "Book ID", "Customer ID", "Ret. Date"};
		
		listTable = new JTable(data,head);
		listTableSP = new JScrollPane(listTable);
		listTableSP.setBounds(350, 100, 400, 150);
		listTable.setEnabled(false);
		panel.add(listTableSP);
		
		loanIdLabel = new JLabel("Loan ID :");
		loanIdLabel.setBounds(100,100,100,30);
		panel.add(loanIdLabel);
		
		loanIdTF = new JTextField();
		loanIdTF.setBounds(220,100,100,30);
		panel.add(loanIdTF);
		
		bookIdLabel = new JLabel("Book ID :");
		bookIdLabel.setBounds(100,150,100,30);
		panel.add(bookIdLabel);
		
		bookIdTF = new JTextField();
		bookIdTF.setBounds(220,150,100,30);
		panel.add(bookIdTF);
		
		cstIdLabel = new JLabel("Customer ID: ");
		cstIdLabel.setBounds(100,200,100,30);
		panel.add(cstIdLabel);
		
		cstIdTF = new JTextField();
		cstIdTF.setBounds(220,200,100,30);
		panel.add(cstIdTF);
		
		returnDateLabel = new JLabel("Return Date: ");
		returnDateLabel.setBounds(100,250,100,30);
		panel.add(returnDateLabel);
		
		returnDateTF = new JTextField();
		returnDateTF.setBounds(220,250,100,30);
		panel.add(returnDateTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(100,300,80,30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		/*insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,300,80,30);
		insertBtn.addActionListener(this);
		panel.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(300,300,80,30);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(400,300,80,30);
		deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		panel.add(deleteBtn);*/
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(500,300,80,30);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		/*getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(500,260,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);*/
		
		backBtn = new JButton("Back");
		backBtn.setBounds(600, 350, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 150, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loadBtn.getText()))
		{
			if(!loanIdTF.getText().equals("") || !loanIdTF.getText().equals(null))
			{
				LoanList l = lr.searchLoanList(loanIdTF.getText());
				if(l!= null)
				{
					String body[][] = new String [1][4];
					body[0][0] = l.getLoanId();
					body[0][1] = l.getBookId();
					body[0][2] = l.getCstId();
					body[0][3] = (l.getReturnDate())+"";
					
					String head[] = {"Loan ID", "Book ID", "Customer ID", "Ret. Date"};
			
					panel.remove(listTableSP);
					
					listTable = new JTable(body,head);
					listTable.setEnabled(false);
					listTableSP = new JScrollPane(listTable);
					listTableSP.setBounds(350, 100, 400, 150);
					panel.add(listTableSP);
					
					panel.revalidate();
					panel.repaint();
					
					bookIdTF.setText(l.getBookId());
					cstIdTF.setText(l.getCstId());
					returnDateTF.setText(l.getReturnDate());
					
					loanIdTF.setEnabled(false);
					bookIdTF.setEnabled(true);
					cstIdTF.setEnabled(true);
					returnDateTF.setEnabled(true);
					
					
					refreshBtn.setEnabled(true);
					
					loadBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		else if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(refreshBtn.getText()))
		{
			loanIdTF.setText("");
			bookIdTF.setText("");
			cstIdTF.setText("");
			returnDateTF.setText("");
			
			loanIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(backBtn.getText()))
		{
			CustomerHome ch = new CustomerHome(user);
			ch.setVisible(true);
			this.setVisible(false);
		}
	}
}