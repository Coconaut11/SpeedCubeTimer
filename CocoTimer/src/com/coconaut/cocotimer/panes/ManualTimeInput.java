package com.coconaut.cocotimer.panes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.coconaut.cocotimer.data.StatsManager;
import com.coconaut.cocotimer.data.Time;
import com.coconaut.cocotimer.data.TimeList;
import com.coconaut.cocotimer.util.TimeUtil;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManualTimeInput extends JFrame {

	private TimeList timelist;
	
	private JPanel contentPane;
	private JTextField textField;

	public ManualTimeInput(TimeList timelist) {
		this.timelist = timelist;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 150);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField("");
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == arg0.VK_ENTER) {
					insert();
				}
			}
		});
		textField.setBounds(25, 28, 144, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(25, 11, 46, 14);
		contentPane.add(lblTime);
		
		JButton btnInsert = new JButton("Insert and Close");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertAndClose();
			}
		});
		btnInsert.setBounds(129, 66, 144, 23);
		contentPane.add(btnInsert);
		
		JButton button = new JButton("Insert");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insert();
			}
		});
		button.setBounds(30, 66, 89, 23);
		contentPane.add(button);
		
		setVisible(true);
	}
	
	private void insert() {
		if(textField.getText().equals("")) return;
		StatsManager.addTime(new Time(TimeUtil.timeToInt(textField.getText())));
		textField.setText("");
		textField.requestFocus();
	}
	
	private void insertAndClose() {
		if(textField.getText().equals("")) return;
		StatsManager.addTime(new Time(TimeUtil.timeToInt(textField.getText())));
		textField.setText("");
		textField.requestFocus();
		dispose();
	}
}
