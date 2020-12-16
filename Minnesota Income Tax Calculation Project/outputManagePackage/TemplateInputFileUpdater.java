package outputManagePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;
import dataManagePackage.Receipt.Receipt;

public abstract class TemplateInputFileUpdater implements InputFileUpdater {
	
	protected PrintWriter outputStream = null;
	
	public void saveUpdatedTaxPayer(String filePath, int taxPayerIndex) {
		
		try {
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem opening: "+filePath);
		}
		
		Database database = Database.getDatabase();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxPayerIndex);
		
		
		writeOutputLine("Name", taxpayer.getName());
		writeOutputLine("AFM", taxpayer.getAFM());
		writeOutputLine("Status", taxpayer.getFamilyStatus());
		writeOutputLine("Income", taxpayer.getIncome());
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			writeOutputLine();
			writeOutputLine("Receipts", 0.0);
			writeOutputLine();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				writeOutputLine("Receipt ID", receipt.getId());
				writeOutputLine("Date", receipt.getDate());
				writeOutputLine("Kind", receipt.getKind());
				writeOutputLine("Amount", receipt.getAmount());
				writeOutputLine("Company", receipt.getCompany().getName());
				writeOutputLine("Country", receipt.getCompany().getCountry());
				writeOutputLine("City", receipt.getCompany().getCity());
				writeOutputLine("Street", receipt.getCompany().getStreet());
				writeOutputLine("Number", receipt.getCompany().getNumber());
				writeOutputLine();
			}	
			
			writeOutputLine("Receipts", 1.0);
		}
		
		outputStream.close();
	}
	
	protected abstract void writeOutputLine();
	protected abstract void writeOutputLine(String title);
	protected abstract void writeOutputLine(String title, double value);
	protected abstract void writeOutputLine(String title, String value);
	
}
