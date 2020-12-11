package gui;
import dataManagePackage.*;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class MainJFrameWindow {

	private JFrame taxationMainWindowJFrame;	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrameWindow window = new MainJFrameWindow();
					window.taxationMainWindowJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainJFrameWindow() {
		initialize();
	}
	
	private void initialize() {
		taxationMainWindowJFrame = new JFrame();
		taxationMainWindowJFrame.setResizable(false);
		taxationMainWindowJFrame.setTitle("���������� ����������");
		taxationMainWindowJFrame.setBounds(-1, -1, 357, 228);
		taxationMainWindowJFrame.setLocationRelativeTo(null);
		taxationMainWindowJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		taxationMainWindowJFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("���. ������� ��������������:");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(30, 11, 218, 33);
		taxationMainWindowJFrame.getContentPane().add(label);
		
		JLabel totalLoadedTaxpayersJLabel = new JLabel("0");
		totalLoadedTaxpayersJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLoadedTaxpayersJLabel.setForeground(Color.RED);
		totalLoadedTaxpayersJLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalLoadedTaxpayersJLabel.setBounds(247, 20, 75, 14);
		taxationMainWindowJFrame.getContentPane().add(totalLoadedTaxpayersJLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 42, 293, 2);
		taxationMainWindowJFrame.getContentPane().add(separator);
		
		JButton openTaxpayerLoadDataJDialog = new JButton("������� ��������� �������������� (-��)");
		openTaxpayerLoadDataJDialog.setFont(new Font("Tahoma", Font.BOLD, 11));
		openTaxpayerLoadDataJDialog.setBounds(27, 55, 295, 53);
		taxationMainWindowJFrame.getContentPane().add(openTaxpayerLoadDataJDialog);
		
		JButton showLoadedTaxpayersDataButton = new JButton("�������� ������ ��������������");
		showLoadedTaxpayersDataButton.setEnabled(false);
		showLoadedTaxpayersDataButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		showLoadedTaxpayersDataButton.setBounds(27, 121, 295, 53);
		taxationMainWindowJFrame.getContentPane().add(showLoadedTaxpayersDataButton);
		
		totalLoadedTaxpayersJLabel.addPropertyChangeListener("text", new PropertyChangeListener() {
		       public void propertyChange(PropertyChangeEvent e) {
		           if (!totalLoadedTaxpayersJLabel.getText().equals("0")){
		        	   showLoadedTaxpayersDataButton.setEnabled(true);
		           }else{
		        	   showLoadedTaxpayersDataButton.setEnabled(false);
		           }
		       }
		 });
		
		openTaxpayerLoadDataJDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser afmInfoFilesFolderChooser = new JFileChooser();
				afmInfoFilesFolderChooser.setCurrentDirectory(new java.io.File("."));
				afmInfoFilesFolderChooser.setDialogTitle("�������� ��� ������ ��� �������� �� <AFM>_INFO.* ������");
				afmInfoFilesFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if(afmInfoFilesFolderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				    String afmInfoFilesFolderPath = afmInfoFilesFolderChooser.getSelectedFile().toString();
				    JOptionPane.showMessageDialog(null, afmInfoFilesFolderPath, "�������� ������� ������� �������", JOptionPane.INFORMATION_MESSAGE);
				    
				    Database.setTaxpayersInfoFilesPath(afmInfoFilesFolderPath);
				    
				    TaxpayerLoadDataJDialog taxpayerLoadDataJDialog = new TaxpayerLoadDataJDialog(taxationMainWindowJFrame);
				    taxpayerLoadDataJDialog.fillTaxpayersAfmInfoFilesJList(afmInfoFilesFolderPath);
				    taxpayerLoadDataJDialog.setVisible(true);
				}
				
			}
		});
		
		showLoadedTaxpayersDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadedTaxpayersJDialog loadedTaxpayersJDialog = new LoadedTaxpayersJDialog(taxationMainWindowJFrame);
				loadedTaxpayersJDialog.fillLoadedTaxpayersJList();
				loadedTaxpayersJDialog.setVisible(true);
			}
		});
	}
}
