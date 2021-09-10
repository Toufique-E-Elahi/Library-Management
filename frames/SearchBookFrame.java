package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class SearchBookFrame extends JFrame implements ActionListener
{
	private JLabel idLabel,nameLabel, authorLabel, quantityLabel;
	private JTextField idTF, nameTF, authorTF, quantityTF;
	JButton logoutBtn,loadBtn, refreshBtn, backBtn, getAllBtn;
	private JPanel panel;
	JTable bkTable;
	JScrollPane bkTableSP;
	
	private User user;
	private BookRepo br;
	private UserRepo ur;
	
	
	public SearchBookFrame(User user)
	{
		super("Search Book");
		this.setSize(800,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		br = new BookRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Book ID", "Name", "Author", "Quantity"};
		
		bkTable = new JTable(data, head);
		bkTableSP = new JScrollPane(bkTable);
		bkTableSP.setBounds(350, 100, 400, 150);
		bkTable.setEnabled(false);
		panel.add(bkTableSP);
		
		idLabel = new JLabel("ID :");
		idLabel.setBounds(100,100,100,30);
		panel.add(idLabel);
		
		idTF = new JTextField();
		idTF.setBounds(220,100,100,30);
		panel.add(idTF);
		
		nameLabel = new JLabel("Name :");
		nameLabel.setBounds(100,150,100,30);
		panel.add(nameLabel);
		
		nameTF = new JTextField();
		nameTF.setBounds(220,150,100,30);
		panel.add(nameTF);
		
		authorLabel = new JLabel("Author: ");
		authorLabel.setBounds(100,200,100,30);
		panel.add(authorLabel);
		
		authorTF = new JTextField();
		authorTF.setBounds(220,200,100,30);
		panel.add(authorTF);
		
		quantityLabel = new JLabel("Quantity: ");
		quantityLabel.setBounds(100,250,100,30);
		panel.add(quantityLabel);
		
		quantityTF = new JTextField();
		quantityTF.setBounds(220,250,100,30);
		panel.add(quantityTF);
		
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
		
		getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(500,260,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
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
			if(!idTF.getText().equals("") || !idTF.getText().equals(null))
			{
				Book b = br.searchBook(idTF.getText());
				if(b!= null)
				{
					String body[][] = new String [1][4];
					body[0][0] = b.getBookId();
					body[0][1] = b.getName();
					body[0][2] = b.getAuthor();
					body[0][3] = (b.getQuantity())+"";
					
					String head[] = {"Book ID", "Name", "Author", "Quantity"};
			
					panel.remove(bkTableSP);
					
					bkTable = new JTable(body,head);
					bkTable.setEnabled(false);
					bkTableSP = new JScrollPane(bkTable);
					bkTableSP.setBounds(350, 100, 400, 150);
					panel.add(bkTableSP);
					
					panel.revalidate();
					panel.repaint();
					
					nameTF.setText(b.getName());
					authorTF.setText(b.getAuthor());
					quantityTF.setText(b.getQuantity()+"");
					
					idTF.setEnabled(false);
					nameTF.setEnabled(true);
					authorTF.setEnabled(true);
					quantityTF.setEnabled(true);
					
					
					
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
			idTF.setText("");
			nameTF.setText("");
			authorTF.setText("");
			quantityTF.setText("");
			
			idTF.setEnabled(true);
			loadBtn.setEnabled(true);
			
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = br.getAllBooks();
			String head[] = {"Book ID", "Name", "Author", "Quantity"};
			
			panel.remove(bkTableSP);
			
			bkTable = new JTable(data,head);
			bkTable.setEnabled(false);
			bkTableSP = new JScrollPane(bkTable);
			bkTableSP.setBounds(350, 100, 400, 150);
			panel.add(bkTableSP);
			
			panel.revalidate();
			panel.repaint();
		}
		else if(command.equals(backBtn.getText()))
		{
			CustomerHome ch = new CustomerHome(user);
			ch.setVisible(true);
			this.setVisible(false);
		}
	}
}