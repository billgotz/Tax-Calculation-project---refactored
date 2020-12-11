package gui;
import dataManagePackage.Database;
import outputManagePackage.OutputSystem;

import java.awt.Color;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadedTaxpayersJDialog extends JDialog {

	private JList loadedTaxpayersJList;
	private JFrame appMainWindow;
	
	
	public LoadedTaxpayersJDialog(JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		setResizable(false);
		setBounds(100, 100, 556, 525);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("��������������");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 280, 400);
		getContentPane().add(scrollPane);
		
		loadedTaxpayersJList = new JList();
		loadedTaxpayersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(loadedTaxpayersJList);
		loadedTaxpayersJList.setForeground(Color.BLUE);
		loadedTaxpayersJList.setFont(new Font("Tahoma", Font.BOLD, 14));
		loadedTaxpayersJList.setVisibleRowCount(100);
		
		JButton showSelectedTaxpayerInfoButton = new JButton();
		showSelectedTaxpayerInfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		String buttonText = "<html>"
				+ "�������� ���������"
				+ "<br>"
				+ "����������� ��������������"
				+ "</html>";
		showSelectedTaxpayerInfoButton.setText(buttonText);
		showSelectedTaxpayerInfoButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		showSelectedTaxpayerInfoButton.setBounds(300, 12, 240, 71);
		getContentPane().add(showSelectedTaxpayerInfoButton);
		
		JButton deleteSelectedTaxpayerFromDatabaseButton = new JButton();
		deleteSelectedTaxpayerFromDatabaseButton.setHorizontalAlignment(SwingConstants.LEFT);
		buttonText = "<html>"
				+ "�������� �����������"
				+ "<br>"
				+ "��������������"
				+ "</html>";
		deleteSelectedTaxpayerFromDatabaseButton.setText(buttonText);
		deleteSelectedTaxpayerFromDatabaseButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		deleteSelectedTaxpayerFromDatabaseButton.setBounds(300, 93, 240, 71);
		getContentPane().add(deleteSelectedTaxpayerFromDatabaseButton);
		
		JButton showSelectedTaxpayerReceiptsButton = new JButton();
		buttonText = "<html>"
				+ "�������� ����������"
				+ "<br>"
				+ "����������� ��������������"
				+ "</html>";
		showSelectedTaxpayerReceiptsButton.setText(buttonText);
		showSelectedTaxpayerReceiptsButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		showSelectedTaxpayerReceiptsButton.setBounds(300, 175, 240, 71);
		getContentPane().add(showSelectedTaxpayerReceiptsButton);
		
		JButton showSelectedTaxpayerPieChartButton = new JButton("��������� ����� ����������");
		showSelectedTaxpayerPieChartButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		showSelectedTaxpayerPieChartButton.setBounds(300, 257, 240, 71);
		getContentPane().add(showSelectedTaxpayerPieChartButton);
		
		JButton showSelectedTaxpayerBarChartButton = new JButton("����������� �������� �����");
		showSelectedTaxpayerBarChartButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		showSelectedTaxpayerBarChartButton.setBounds(300, 340, 240, 71);
		getContentPane().add(showSelectedTaxpayerBarChartButton);
		
		JButton saveSelectedTaxpayerInfoToTxtButton = new JButton("���������� ��������� �������������� �� txt");
		saveSelectedTaxpayerInfoToTxtButton.setForeground(Color.RED);
		saveSelectedTaxpayerInfoToTxtButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		saveSelectedTaxpayerInfoToTxtButton.setBounds(10, 422, 530, 29);
		getContentPane().add(saveSelectedTaxpayerInfoToTxtButton);
		
		JButton saveSelectedTaxpayerInfoToXmlButton = new JButton("���������� ��������� �������������� �� xml");
		saveSelectedTaxpayerInfoToXmlButton.setForeground(Color.RED);
		saveSelectedTaxpayerInfoToXmlButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		saveSelectedTaxpayerInfoToXmlButton.setBounds(10, 455, 530, 29);
		getContentPane().add(saveSelectedTaxpayerInfoToXmlButton);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) loadedTaxpayersJList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		showSelectedTaxpayerInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (loadedTaxpayersJList.getSelectedIndex()!=-1){
					JOptionPane.showMessageDialog(null, Database.getTaxpayerFromArrayList(loadedTaxpayersJList.getSelectedIndex()).toString(),  loadedTaxpayersJList.getSelectedValue().toString(), JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������� ������������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		deleteSelectedTaxpayerFromDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (loadedTaxpayersJList.getSelectedIndex()!=-1){
					int dialogResult = JOptionPane.showConfirmDialog (null, "�������� ����������� ��������������("+loadedTaxpayersJList.getSelectedValue().toString()+") ��� ��� ���� ���������?", "����������� ���������", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						Database.removeTaxpayerFromArrayList(loadedTaxpayersJList.getSelectedIndex());
						
						fillLoadedTaxpayersJList();
						
						JLabel totalLoadedTaxpayersJLabel = (JLabel)appMainWindow.getContentPane().getComponent(1);
						totalLoadedTaxpayersJLabel.setText(Integer.toString(Database.getTaxpayersArrayListSize()));
						
						if (Database.getTaxpayersArrayListSize()==0) dispose();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������� ������������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		showSelectedTaxpayerReceiptsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (loadedTaxpayersJList.getSelectedIndex()!=-1){
					TaxpayerReceiptsManagementJDialog taxpayerReceiptsManagementJDialog = new TaxpayerReceiptsManagementJDialog(loadedTaxpayersJList.getSelectedValue().toString(), loadedTaxpayersJList.getSelectedIndex());
					taxpayerReceiptsManagementJDialog.fillTaxpayerReceiptsJList();
					taxpayerReceiptsManagementJDialog.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������� ������������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		showSelectedTaxpayerPieChartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int taxpayerIndex = loadedTaxpayersJList.getSelectedIndex();
				if (taxpayerIndex!=-1){
					OutputSystem.createTaxpayerReceiptsPieJFreeChart(taxpayerIndex);
				}
				else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������� ������������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		showSelectedTaxpayerBarChartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int taxpayerIndex = loadedTaxpayersJList.getSelectedIndex();
				if (taxpayerIndex!=-1){
					OutputSystem.createTaxpayerTaxAnalysisBarJFreeChart(taxpayerIndex);
				}
				else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������� ������������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		saveSelectedTaxpayerInfoToTxtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int taxpayerIndex = loadedTaxpayersJList.getSelectedIndex();
				if (taxpayerIndex!=-1){
					JFileChooser saveFileFolderChooser = new JFileChooser();
					saveFileFolderChooser.setCurrentDirectory(new java.io.File("."));
					saveFileFolderChooser.setDialogTitle("�������� ������ ����������� "+Database.getTaxpayerFromArrayList(taxpayerIndex).getAFM()+"_LOG.txt");
					saveFileFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					
					if(saveFileFolderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					    String savePath = saveFileFolderChooser.getSelectedFile().toString();
					    
					    OutputSystem.saveTaxpayerInfoToTxtLogFile(savePath, taxpayerIndex);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������� ������������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		saveSelectedTaxpayerInfoToXmlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int taxpayerIndex = loadedTaxpayersJList.getSelectedIndex();
				if (taxpayerIndex!=-1){
					JFileChooser saveFileFolderChooser = new JFileChooser();
					saveFileFolderChooser.setCurrentDirectory(new java.io.File("."));
					saveFileFolderChooser.setDialogTitle("�������� ������ ����������� "+Database.getTaxpayerFromArrayList(taxpayerIndex).getAFM()+"_LOG.xml");
					saveFileFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					
					if(saveFileFolderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					    String savePath = saveFileFolderChooser.getSelectedFile().toString();
					    
					    OutputSystem.saveTaxpayerInfoToXmlLogFile(savePath, taxpayerIndex);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������� ������������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
	public void fillLoadedTaxpayersJList(){
		String[] jlistValues = Database.getTaxpayersNameAfmValuesPairList();
		
		loadedTaxpayersJList.setModel(new AbstractListModel() {
			String[] values = jlistValues;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
}
