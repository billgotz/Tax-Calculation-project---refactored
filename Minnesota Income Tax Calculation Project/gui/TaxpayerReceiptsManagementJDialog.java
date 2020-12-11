package gui;
import dataManagePackage.*;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import dataManagePackage.Database;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaxpayerReceiptsManagementJDialog extends JDialog {

	private JList taxpayerReceiptsJList;
	private int taxpayerID;
	
	
	public TaxpayerReceiptsManagementJDialog(String windowTitle, int taxpayerID) {
		this.taxpayerID = taxpayerID;
		
		setResizable(false);
		setBounds(100, 100, 480, 460);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 250, 384);
		getContentPane().add(scrollPane);
		setTitle(windowTitle);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		taxpayerReceiptsJList = new JList();
		scrollPane.setViewportView(taxpayerReceiptsJList);
		taxpayerReceiptsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taxpayerReceiptsJList.setForeground(Color.BLUE);
		taxpayerReceiptsJList.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label = new JLabel("���������� ��������������");
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 11, 250, 22);
		getContentPane().add(label);		
		
		JButton insertNewReceiptButton = new JButton("�������� ���� ���������");
		insertNewReceiptButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		insertNewReceiptButton.setBounds(270, 114, 194, 65);
		getContentPane().add(insertNewReceiptButton);
		
		JButton deleteSelectedReceiptButton = new JButton();
		deleteSelectedReceiptButton.setHorizontalAlignment(SwingConstants.LEFT);
		String buttonText = "<html>"
				+ "�������� �����������"
				+ "<br>"
				+ "���������"
				+ "</html>";
		deleteSelectedReceiptButton.setText(buttonText);
		deleteSelectedReceiptButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		deleteSelectedReceiptButton.setBounds(270, 190, 194, 65);
		getContentPane().add(deleteSelectedReceiptButton);
		
		JButton showSelectedReceiptDetailsButton = new JButton();
		buttonText = "<html>"
				+ "�������� �����������"
				+ "<br>"
				+ "����������� ���������"
				+ "</html>";
		showSelectedReceiptDetailsButton.setText(buttonText);
		showSelectedReceiptDetailsButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		showSelectedReceiptDetailsButton.setBounds(270, 38, 194, 65);
		getContentPane().add(showSelectedReceiptDetailsButton);
		
		showSelectedReceiptDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (taxpayerReceiptsJList.getSelectedIndex()!=-1){
					JOptionPane.showMessageDialog(null, Database.getTaxpayerFromArrayList(taxpayerID).getReceipt(taxpayerReceiptsJList.getSelectedIndex()).toString(), taxpayerReceiptsJList.getSelectedValue().toString(), JOptionPane.PLAIN_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������ �������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		insertNewReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InsertNewReceiptJDialog insertNewReceiptJDialog = new InsertNewReceiptJDialog(taxpayerID);
				insertNewReceiptJDialog.setVisible(true);
				
				dispose();
			}
		});
		
		deleteSelectedReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (taxpayerReceiptsJList.getSelectedIndex()!=-1){
					int dialogResult = JOptionPane.showConfirmDialog (null, "�������� ����������� ���������("+taxpayerReceiptsJList.getSelectedValue().toString()+") ?", "����������� ���������", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						Database.getTaxpayerFromArrayList(taxpayerID).removeReceiptFromList(taxpayerReceiptsJList.getSelectedIndex());
						
						Database.updateTaxpayerInputFile(taxpayerID);
						
						fillTaxpayerReceiptsJList();
					}
				}else{
					JOptionPane.showMessageDialog(null, "��� ����� �������� ������ �������� ��� ��� �����.", "������", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
	public void fillTaxpayerReceiptsJList(){
		String[] jlistValues = Database.getTaxpayerFromArrayList(taxpayerID).getReceiptsList();
		
		taxpayerReceiptsJList.setModel(new AbstractListModel() {
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
