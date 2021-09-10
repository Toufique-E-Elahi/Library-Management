package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class ListFrame extends JFrame implements ActionListener
{
	private JLabel loanIdLabel,bookIdLabel, cstIdLabel, returnDateLabel;
	private JTextField loanIdTF, bookIdTF, cstIdTF, returnDateTF;
	private JButton logoutBtn,loadBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, backBtn,getAllBtn;
	private JPanel panel;
	private JTable listTable;
	private JScrollPane listTableSP;
	
	private User user;
	private LoanListRepo lr;
	private UserRepo ur;
	
	
	public ListFrame(User user)
	{
		super("LoanList Frame");
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
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 150, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
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
		
		insertBtn = new JButton("Insert");
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
		panel.add(deleteBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(500,300,80,30);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(500,260,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(600, 350, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
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
					
					insertBtn.setEnabled(false);
					refreshBtn.setEnabled(true);
					updateBtn.setEnabled(true);
					loadBtn.setEnabled(false);
					deleteBtn.setEnabled(true);
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
		else if(command.equals(insertBtn.getText()))
		{
			LoanList l = new LoanList();
			
			
			l.setLoanId(loanIdTF.getText());
			l.setBookId(bookIdTF.getText());
			l.setCstId(cstIdTF.getText());
			l.setReturnDate(returnDateTF.getText());
			
			lr.insertInDB(l);
			
			loanIdTF.setText("");
			bookIdTF.setText("");
			cstIdTF.setText("");
			returnDateTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			loanIdTF.setText("");
			bookIdTF.setText("");
			cstIdTF.setText("");
			returnDateTF.setText("");
			
			loanIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			LoanList l = new LoanList();
			
			l.setLoanId(loanIdTF.getText());
			l.setBookId(bookIdTF.getText());
			l.setCstId(cstIdTF.getText());
			l.setReturnDate(returnDateTF.getText());
			
			lr.updateInDB(l);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			loanIdTF.setText("");
			bookIdTF.setText("");
			cstIdTF.setText("");
			returnDateTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			loanIdTF.setEnabled(true);
			bookIdTF.setEnabled(true);
			cstIdTF.setEnabled(true);
			returnDateTF.setEnabled(true);
		}
		else if(command.equals(deleteBtn.getText()))
		{
			lr.deleteFromDB(loanIdTF.getText());
			
			
			JOptionPane.showMessageDialog(this,"Deleted");
			
			loanIdTF.setText("");
			bookIdTF.setText("");
			cstIdTF.setText("");
			returnDateTF.setText("");
			
			loanIdTF.setEnabled(true);
			bookIdTF.setEnabled(true);
			cstIdTF.setEnabled(true);
			returnDateTF.setEnabled(true);
	
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = lr.getAllLoanList();
			String head[] = {"Loan ID", "Book ID", "Customer ID", "Ret. Date"};
			
			panel.remove(listTableSP);
			
			listTable = new JTable(data,head);
			listTable.setEnabled(false);
			listTableSP = new JScrollPane(listTable);
			listTableSP.setBounds(350, 100, 400, 150);
			panel.add(listTableSP);
			
			panel.revalidate();
			panel.repaint();
		}
		else if(command.equals(backBtn.getText()))
		{
			EmployeeHome eh = new EmployeeHome(user);
			eh.setVisible(true);
			this.setVisible(false);
		}
	}
	/*public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loadBtn.getText()))
		{
			if(!empIdTF.getText().equals("") || !empIdTF.getText().equals(null))
			{
				Employee e = er.searchEmployee(empIdTF.getText());
				if(e!= null)
				{
					empNameTF.setText(e.getName());
					empDesignationTF.setText(e.getDesignation());
					empSalaryTF.setText(e.getSalary()+"");
					
					empIdTF.setEnabled(false);
					empNameTF.setEnabled(true);
					empDesignationTF.setEnabled(true);
					empSalaryTF.setEnabled(true);
					
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					loadBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		else if(command.equals(insertBtn.getText()))
		{
			Employee e = new Employee();
			User u = new User();
			Random rd = new Random();
			int x = rd.nextInt(9999999)+10000000;
			
			e.setEmpId(empIdTF.getText());
			e.setName(empNameTF.getText());
			e.setDesignation(empDesignationTF.getText());
			e.setSalary(Double.parseDouble(empSalaryTF.getText()));
			
			u.setUserId(empIdTF.getText());
			u.setPassword(x+"");
			
			if(((empDesignationTF.getText()).equals("Manager")) || ((empDesignationTF.getText()).equals("manager")))
			{
				u.setStatus(0);
			}
			else
			{
				u.setStatus(1);
			}
			
			er.insertInDB(e);
			ur.insertUser(u);
			
			JOptionPane.showMessageDialog(this, "Inserted, Id: "+empIdTF.getText()+"and Password: "+x);
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			empIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			Employee e = new Employee();
			
			e.setEmpId(empIdTF.getText());
			e.setName(empNameTF.getText());
			e.setDesignation(empDesignationTF.getText());
			e.setSalary(Double.parseDouble(empSalaryTF.getText()));
			
			er.updateInDB(e);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			empIdTF.setEnabled(true);
			empNameTF.setEnabled(true);
			empDesignationTF.setEnabled(true);
			empSalaryTF.setEnabled(true);
		}
		else if(command.equals(deleteBtn.getText()))
		{
			er.deleteFromDB(empIdTF.getText());
			ur.deleteUser(empIdTF.getText());
			
			JOptionPane.showMessageDialog(this,"Deleted");
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			empIdTF.setEnabled(true);
			empNameTF.setEnabled(true);
			empDesignationTF.setEnabled(true);
			empSalaryTF.setEnabled(true);
	
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		/*else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = er.getAllEmployee();
			String head[] = {"Id", "Name", "Designation", "Salary"};
			
			panel.remove(empTableSP);
			
			empTable = new JTable(data,head);
			empTable.setEnabled(false);
			empTableSP = new JScrollPane(empTable);
			empTableSP.setBounds(350, 100, 400, 150);
			panel.add(empTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		else if(command.equals(backBtn.getText()))
		{
			EmployeeHome eh = new EmployeeHome(user);
			eh.setVisible(true);
			this.setVisible(false);
		}
		else{}
		
	}*/
}