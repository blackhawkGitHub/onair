package de.rinke.ui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PersonalEditorUI extends JDialog {

	private JPanel contentPane;
	private JTextField tf_id;
	private JTextField tf_namemodell;
	private JPanel panel;
	private JButton btnOK;
	private JButton btnAbbrechen;
	private JTextField tf_ort;
	private JLabel lblNewLabel_3;

	/**
	 * Create the frame.
	 */
	public PersonalEditorUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("ID");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		tf_id = new JTextField();
		tf_id.setEnabled(false);
		tf_id.setEditable(false);
		GridBagConstraints gbc_tf_id = new GridBagConstraints();
		gbc_tf_id.insets = new Insets(0, 0, 5, 0);
		gbc_tf_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_id.gridx = 1;
		gbc_tf_id.gridy = 0;
		contentPane.add(tf_id, gbc_tf_id);
		tf_id.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Namel");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		tf_namemodell = new JTextField();
		GridBagConstraints gbc_tf_namemodell = new GridBagConstraints();
		gbc_tf_namemodell.insets = new Insets(0, 0, 5, 0);
		gbc_tf_namemodell.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_namemodell.gridx = 1;
		gbc_tf_namemodell.gridy = 2;
		contentPane.add(tf_namemodell, gbc_tf_namemodell);
		tf_namemodell.setColumns(10);

		lblNewLabel_3 = new JLabel("Ort");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		tf_ort = new JTextField();
		GridBagConstraints gbc_tf_ort = new GridBagConstraints();
		gbc_tf_ort.insets = new Insets(0, 0, 5, 0);
		gbc_tf_ort.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_ort.gridx = 1;
		gbc_tf_ort.gridy = 3;
		contentPane.add(tf_ort, gbc_tf_ort);
		tf_ort.setColumns(10);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnOK = new JButton("ok");
		panel.add(btnOK);

		btnAbbrechen = new JButton("abbrechen");
		panel.add(btnAbbrechen);
	}

	public JTextField getTf_id() {
		return tf_id;
	}

	public JTextField getTf_namemodell() {
		return tf_namemodell;
	}

	public JButton getBtnOK() {
		return btnOK;
	}

	public JButton getBtnAbbrechen() {
		return btnAbbrechen;
	}

	public JTextField getTf_ort() {
		return tf_ort;
	}
}
