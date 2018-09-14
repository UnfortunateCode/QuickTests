package learn;

import javax.swing.*;

public class CelsiusConverterGUI extends JFrame {
	private JTextField tempTextField;
	private JLabel celsiusLabel, fahrenheitLabel;
	private JButton convertButton;

	public CelsiusConverterGUI() {
		initComponents();
	}
	
	private void initComponents() {
		tempTextField = new JTextField();
		celsiusLabel = new JLabel();
		convertButton = new JButton();
		fahrenheitLabel = new JLabel();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Celsius Converter");
		
		celsiusLabel.setText("Celsius");
		
		convertButton.setText("Convert");
		convertButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				convertButtonActionPerformed(evt);
			}
		});
		
		fahrenheitLabel.setText("Fahrenheit");
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(celsiusLabel))
								.addGroup(layout.createSequentialGroup()
										.addComponent(convertButton)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(fahrenheitLabel)))
						.addContainerGap(27, Short.MAX_VALUE))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {convertButton, tempTextField});
		
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(celsiusLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(convertButton)
								.addComponent(fahrenheitLabel))
						.addContainerGap(21, Short.MAX_VALUE))
		);
		pack();
	}
	
	
	private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			int tempFahr = (int)((Double.parseDouble(tempTextField.getText())) * 1.8 + 32);
			fahrenheitLabel.setText(tempFahr + " Fahrenheit");
		} catch (NumberFormatException nfe) {
			fahrenheitLabel.setText("Fahrenheit");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CelsiusConverterGUI().setVisible(true);
			}
		});

	}

}
