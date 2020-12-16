package inputManagePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dataManagePackage.Database;
import dataManagePackage.Receipt;
import dataManagePackage.Taxpayer;

public abstract class Parse {
		
	public void loadTaxPayerData(String afmInfoFileFolderPath, String afmInfoFile) {
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream(afmInfoFileFolderPath+"\\"+afmInfoFile));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening " + afmInfoFile + " file.");
			System.exit(0);
		}
		
		Taxpayer newTaxpayer = addTaxpayer(inputStream);

		String fileLine;
		while(inputStream.hasNextLine()) {
			fileLine = inputStream.nextLine();
			if (fileLine.equals("")) continue;
			if (skipLine(fileLine)) continue;
			if (endOfFile(fileLine)) break;
			
			Receipt newReceipt = addReceipt(inputStream, fileLine);
			newTaxpayer.addReceiptToList(newReceipt);
		}
		Database.addTaxpayerToList(newTaxpayer);
	}
	
	protected abstract Taxpayer addTaxpayer(Scanner inputStream);
	protected abstract Receipt addReceipt(Scanner inputStream, String fileLine);
	protected abstract boolean skipLine(String fileLine);
	protected abstract boolean endOfFile(String fileLine);
}
