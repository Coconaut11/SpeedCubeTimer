package com.coconaut.cocotimer.panes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.coconaut.cocotimer.data.StatsManager;
import com.coconaut.cocotimer.data.Time;
import com.coconaut.cocotimer.data.TimeList;
import com.coconaut.cocotimer.util.AverageUtil;
import com.coconaut.cocotimer.util.TimeUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SessionInfoPane extends JFrame {

	private JPanel contentPane;

	public SessionInfoPane() {
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(650, 450);
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
		btnClose.setBounds(545, 387, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblSadistics = new JLabel("Sadistics:");
		lblSadistics.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSadistics.setBounds(28, 11, 123, 23);
		contentPane.add(lblSadistics);
		
		JLabel lblNumberOfSolutions = new JLabel("Solves:");
		lblNumberOfSolutions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumberOfSolutions.setBounds(497, 16, 89, 18);
		contentPane.add(lblNumberOfSolutions);

		JLabel lblNewLabel = new JLabel(StatsManager.getLength() + "");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(558, 18, 40, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAverage = new JLabel("Average:");
		lblAverage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAverage.setBounds(28, 45, 155, 14);
		contentPane.add(lblAverage);
		
		Time time = new Time(AverageUtil.average(StatsManager.getTimes()));
		
		JLabel lblXxxxxx = new JLabel(TimeUtil.bulidTimeString(TimeUtil.intToTime(time.getTicks())));
		lblXxxxxx.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblXxxxxx.setBounds(193, 45, 72, 14);
		contentPane.add(lblXxxxxx);
		
		JLabel lblBest = new JLabel("Best:");
		lblBest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBest.setBounds(28, 70, 155, 14);
		contentPane.add(lblBest);
		
		JLabel label_1 = new JLabel(StatsManager.bestTime.getTime());
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(193, 70, 72, 14);
		contentPane.add(label_1);
		
		JLabel lblWorst = new JLabel("Worst");
		lblWorst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWorst.setBounds(28, 95, 155, 14);
		contentPane.add(lblWorst);
		
		JLabel lblWorst_1 = new JLabel(StatsManager.worstTime.getTime());
		lblWorst_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWorst_1.setBounds(193, 95, 72, 14);
		contentPane.add(lblWorst_1);
		
		JLabel lblAverageOf = new JLabel("Average of 5");
		lblAverageOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAverageOf.setBounds(28, 120, 155, 14);
		contentPane.add(lblAverageOf);
		
		JLabel label_2 = new JLabel(StatsManager.currentAOF.getTime());
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(193, 120, 72, 14);
		contentPane.add(label_2);
		
		JLabel lblAverageOf_1 = new JLabel("Average of 12");
		lblAverageOf_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAverageOf_1.setBounds(28, 145, 155, 14);
		contentPane.add(lblAverageOf_1);
		
		JLabel label_3 = new JLabel(StatsManager.currentAOT.getTime());
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(193, 145, 72, 14);
		contentPane.add(label_3);
		
		JLabel lblBestAverageOf = new JLabel("Best average of 5");
		lblBestAverageOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBestAverageOf.setBounds(28, 170, 155, 14);
		contentPane.add(lblBestAverageOf);
		
		JLabel label_4 = new JLabel(StatsManager.bestAOF.getTime());
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(193, 170, 72, 14);
		contentPane.add(label_4);
		
		JLabel lblBestAverageOf_1 = new JLabel("Best average of 12");
		lblBestAverageOf_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBestAverageOf_1.setBounds(28, 195, 155, 14);
		contentPane.add(lblBestAverageOf_1);
		
		JLabel label_5 = new JLabel(StatsManager.bestAOT.getTime());
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBounds(193, 195, 72, 14);
		contentPane.add(label_5);
		
		JTextArea txtCaca = new JTextArea(StatsManager.getSessionInfoAsString());
		JScrollPane scroll = new JScrollPane(txtCaca);
		txtCaca.setEditable(false);
		scroll.setBounds(28, 220, 606, 156);
		contentPane.add(scroll);
				
		setVisible(true);
	}
}
