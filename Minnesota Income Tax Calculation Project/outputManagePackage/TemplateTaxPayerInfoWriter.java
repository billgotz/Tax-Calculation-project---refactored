package outputManagePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public abstract class TemplateTaxPayerInfoWriter implements TaxPayerInfoWriter {
	
	protected PrintWriter outputStream = null;
	
	public void saveTaxPayerInfo(String folderSavePath, int taxPayerIndex) {
		
		Database database = Database.getDatabase();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxPayerIndex);

		try
		{
			outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt");
		}
		writeOutputLine("Name", taxpayer.getName());
		writeOutputLine("AFM", taxpayer.getAFM());
		writeOutputLine("Income", taxpayer.getIncome());
		writeOutputLine("Basic Tax", taxpayer.getBasicTax());
		
		if (taxpayer.getTaxInxrease()!=0){
			writeOutputLine("Tax Increase", taxpayer.getTaxInxrease());
		}else{
			writeOutputLine("Tax Decrease", taxpayer.getTaxDecrease());
		}
		writeOutputLine("Total Tax", taxpayer.getTotalTax());
		writeOutputLine("Total Receipts Amount", taxpayer.getTotalReceiptsAmount());
		writeOutputLine("Entertainment", taxpayer.getEntertainmentReceiptsTotalAmount());
		writeOutputLine("Basic", taxpayer.getBasicReceiptsTotalAmount());
		writeOutputLine("Travel", taxpayer.getTravelReceiptsTotalAmount());
		writeOutputLine("Health", taxpayer.getHealthReceiptsTotalAmount());
		writeOutputLine("Other", taxpayer.getOtherReceiptsTotalAmount());
		
		outputStream.close();
		
		JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	protected abstract void writeOutputLine(String title, double value);
	protected abstract void writeOutputLine(String title, String value);
}
