package outputManagePackage;

import java.io.PrintWriter;

import javax.swing.JOptionPane;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public abstract class TemplateTaxPayerInfoWriter implements TaxPayerInfoWriter {
	
	protected PrintWriter outputStream = null;
	protected Taxpayer taxpayer;
	
	public void saveTaxPayerInfo(String folderSavePath, int taxPayerIndex) {
		
		Database database = Database.getDatabase();
		taxpayer = database.getTaxpayerFromArrayList(taxPayerIndex);
		
		outputStream = createOutputStream(folderSavePath);
		
		
		writeOutputLine("Name", taxpayer.getName());
		writeOutputLine("AFM", taxpayer.getAFM());
		writeOutputLine("Income", taxpayer.getIncome());
		writeOutputLine("Basic Tax", taxpayer.getBasicTax());
		
		if (taxpayer.getTaxIncrease()!=0){
			writeOutputLine("Tax Increase", taxpayer.getTaxIncrease());
		}else{
			writeOutputLine("Tax Decrease", taxpayer.getTaxDecrease());
		}
		writeOutputLine("Total Tax", taxpayer.getTotalTax());
		writeOutputLine("Total Receipts Amount", taxpayer.getTotalReceiptsAmount());
		writeOutputLine("Entertainment", taxpayer.getReceiptAmount("Entertainment"));
		writeOutputLine("Basic", taxpayer.getReceiptAmount("Basic"));
		writeOutputLine("Travel", taxpayer.getReceiptAmount("Travel"));
		writeOutputLine("Health", taxpayer.getReceiptAmount("Health"));
		writeOutputLine("Other", taxpayer.getReceiptAmount("Other"));
		
		outputStream.close();
		
		JOptionPane.showMessageDialog(null, "Η αποθήκευση ολοκληρώθηκε", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	protected abstract PrintWriter createOutputStream(String folderSavePath);

	protected abstract void writeOutputLine(String title, double value);
	protected abstract void writeOutputLine(String title, String value);
}
