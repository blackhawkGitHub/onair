package de.rinke.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OnAirUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnAircraft;
	private JScrollPane scrollPane;
	private JTextField tf_airport;

	/**
	 * Create the frame.
	 */
	public OnAirUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);

		btnAircraft = new JButton("aircraft");
		panel.add(btnAircraft);

		tf_airport = new JTextField();
		panel.add(tf_airport);
		tf_airport.setColumns(10);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public JButton getBtnAircraft() {
		return btnAircraft;
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getTf_airport() {
		return tf_airport;
	}
}
