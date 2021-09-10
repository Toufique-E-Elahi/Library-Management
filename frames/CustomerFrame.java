package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class CustomerFrame extends JFrame implements ActionListener
{
	private JLabel cstIdLabel, nameLabel, addressLabel,emailLabel;
	private JTextField cstIdTF, nameTF, addressTF, emailTF;
	private JButton logoutBtn,loadBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, getAllBtn, backBtn;
	private JPanel panel;
	
	private LoginFrame lf;
	CustomerRepo cr;
	UserRepo ur;
	User user;
	
	
	public CustomerFrame(User user)
	{
		super("Customer Frame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		cr = new CustomerRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		/*String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Id", "Name", "Designation", "Salary"};*/
		
		/*empTable = new JTable(data,head);
		empTableSP = new JScrollPane(empTable);
		empTableSP.setBounds(350, 100, 400, 150);
		empTable.setEnabled(false);
		panel.add(empTableSP);*/
		
		cstIdLabel = new JLabel("Customer ID :");
		cstIdLabel.setBounds(100,100,100,30);
		panel.add(cstIdLabel);
		
		cstIdTF = new JTextField(user.getUserId());
		cstIdTF.setBounds(220,100,100,30);
		cstIdTF.setEnabled(false);
		panel.add(cstIdTF);
		
		nameLabel = new JLabel("Customer Name :");
		nameLabel.setBounds(100,150,100,30);
		panel.add(nameLabel);
		
		nameTF = new JTextField();
		nameTF.setBounds(220,150,100,30);
		panel.add(nameTF);
		
		addressLabel = new JLabel("Adress: ");
		addressLabel.setBounds(100,200,100,30);
		panel.add(addressLabel);
		
		addressTF = new JTextField();
		addressTF.setBounds(220,200,100,30);
		panel.add(addressTF);
		
		emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(100,250,100,30);
		panel.add(emailLabel);
		
		emailTF = new JTextField();
		emailTF.setBounds(220,250,100,30);
		panel.add(emailTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(100,300,80,30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		/*insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,300,80,30);
		//insertBtn.addActionListener(this);
		panel.add(insertBtn);*/
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(300,300,80,30);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		/*deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(400,300,80,30);
		//deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		panel.add(deleteBtn);*/
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(500,300,80,30);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		/*getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(500,260,80,30);
		//getAllBtn.addActionListener(this);
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
			if(!cstIdTF.getText().equals("") || !cstIdTF.getText().equals(null))
			{
				Customer c = cr.searchCustomer(cstIdTF.getText());
				if(c!= null)
				{
					nameTF.setText(c.getName());
					addressTF.setText(c.getAddress());
					emailTF.setText(c.getEmail());
					
					cstIdTF.setEnabled(false);
					nameTF.setEnabled(true);
					addressTF.setEnabled(true);
					emailTF.setEnabled(true);
					
					updateBtn.setEnabled(true);
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
			
			nameTF.setText("");
			addressTF.setText("");
			emailTF.setText("");
			
			cstIdTF.setEnabled(false);
			loadBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			Customer c = new Customer();
			
			c.setCstId(cstIdTF.getText());
			c.setName(nameTF.getText());
			c.setAddress(addressTF.getText());
			c.setEmail(emailTF.getText());
			
			cr.updateInDB(c);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			
			nameTF.setText("");
			addressTF.setText("");
			emailTF.setText("");
			
			loadBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			cstIdTF.setEnabled(false);
			nameTF.setEnabled(true);
			addressTF.setEnabled(true);
			emailTF.setEnabled(true);
		}
		else if(command.equals(backBtn.getText()))
		{
			CustomerHome ch = new CustomerHome(user);
			ch.setVisible(true);
			this.setVisible(false);
		}
		else{}
		
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