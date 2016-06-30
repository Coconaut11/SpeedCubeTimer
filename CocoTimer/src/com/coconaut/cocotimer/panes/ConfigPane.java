package com.coconaut.cocotimer.panes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.coconaut.cocotimer.Game;
import com.coconaut.cocotimer.util.FileUtil;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConfigPane extends JFrame {
	
	public static boolean inspection = false;
	public static boolean saveTimes = true;
	public static boolean askOnClose = true;
	public static boolean scramble = true;
	public static int scrambleSize = 20;

	private JPanel contentPane;
	private JTextField textField;

	public ConfigPane() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(335, 227, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblConfiguration = new JLabel("Configuration");
		lblConfiguration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfiguration.setBounds(10, 11, 138, 25);
		contentPane.add(lblConfiguration);
		
		JLabel lblInspection = new JLabel("Inspection");
		lblInspection.setBounds(20, 47, 101, 14);
		contentPane.add(lblInspection);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					inspection = true;
				} else {
					inspection = false;
				}
			}
		});
		checkBox.setEnabled(false);
		checkBox.setSelected(inspection);
		checkBox.setToolTipText("Don't you want to inspect?");
		checkBox.setBounds(127, 43, 21, 23);
		contentPane.add(checkBox);
		
		JLabel lblSaveTimes = new JLabel("Save Times");
		lblSaveTimes.setBounds(232, 47, 101, 14);
		contentPane.add(lblSaveTimes);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					saveTimes = true;
				} else {
					saveTimes = false;
				}
			}
		});
		checkBox_1.setToolTipText("Save it?");
		checkBox_1.setEnabled(true);
		checkBox_1.setSelected(saveTimes);
		checkBox_1.setBounds(339, 43, 21, 23);
		contentPane.add(checkBox_1);
		
		JLabel lblAskOnClose = new JLabel("Ask on close");
		lblAskOnClose.setBounds(232, 72, 101, 14);
		contentPane.add(lblAskOnClose);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					askOnClose = true;
				} else {
					askOnClose = false;
				}
			}
		});
		checkBox_3.setToolTipText("Let me ask you! Please!");
		checkBox_3.setEnabled(true);
		checkBox_3.setSelected(askOnClose);
		checkBox_3.setBounds(339, 68, 21, 23);
		contentPane.add(checkBox_3);
		
		JLabel lblScramble = new JLabel("Scramble");
		lblScramble.setBounds(20, 72, 101, 14);
		contentPane.add(lblScramble);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					scramble = true;
				} else {
					scramble = false;
				}
			}
		});
		checkBox_4.setToolTipText("Do you want a scramble");
		checkBox_4.setEnabled(true);
		checkBox_4.setSelected(scramble);
		checkBox_4.setBounds(127, 68, 21, 23);
		contentPane.add(checkBox_4);
		
		JLabel lblScrambleSize = new JLabel("Scramble size");
		lblScrambleSize.setBounds(20, 171, 101, 14);
		contentPane.add(lblScrambleSize);
		
		textField = new JTextField();
		textField.setToolTipText("Scramble Size");
		textField.setText(scrambleSize + "");
		textField.setBounds(117, 168, 43, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apply();
			}
		});
		btnApply.setBounds(170, 167, 71, 21);
		contentPane.add(btnApply);
		
		setVisible(true);
		
		apply();
	}
	
	private void apply() {
		scrambleSize = Integer.parseInt(textField.getText());
		Game.reloadScramble();
	}
	
	public static void loadConfig() {
		String[] config = FileUtil.readFile("savedata/config.ct").split(",");
		
		inspection = Boolean.parseBoolean(config[0]);
		scramble = Boolean.parseBoolean(config[1]);
		saveTimes = Boolean.parseBoolean(config[2]);
		askOnClose = Boolean.parseBoolean(config[3]);
		scrambleSize = Integer.parseInt(config[4]);
	}

	public static void saveConfig() {
		String toWrite = "";
		
		toWrite = inspection + "," +
				scramble + "," +
				saveTimes + "," +
				askOnClose + "," +
				scrambleSize;
		
		FileUtil.writeFile(toWrite, "savedata/config.ct");
	}
}
