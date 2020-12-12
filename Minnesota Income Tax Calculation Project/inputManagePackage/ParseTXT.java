package inputManagePackage;

import java.util.Scanner;
import dataManagePackage.Receipt;
import dataManagePackage.Taxpayer;

public class ParseTXT extends Parse{

	public ParseTXT(String folderPath, String file) {
		loadTaxPayerData(folderPath, file);
	}

	public String getParameterValue(String fileLine, String parameterName){
		return fileLine.substring(parameterName.length(), fileLine.length());
	}

	@Override
	protected Taxpayer addTaxpayer(Scanner inputStream) {
		String taxpayerName = getParameterValue(inputStream.nextLine(), "Name: ");
		String taxpayerAFM = getParameterValue(inputStream.nextLine(), "AFM: ");
		String taxpayerStatus = getParameterValue(inputStream.nextLine(), "Status: ");
		String taxpayerIncome = getParameterValue(inputStream.nextLine(), "Income: ");
		
		return new Taxpayer(taxpayerName, taxpayerAFM, taxpayerIncome, taxpayerStatus);
	}

	@Override
	protected Receipt addReceipt(Scanner inputStream, String fileLine) {
		String receiptID = getParameterValue(fileLine, "Receipt ID: ");
		String receiptDate = getParameterValue(inputStream.nextLine(), "Date: ");
		String receiptKind = getParameterValue(inputStream.nextLine(), "Kind: ");
		String receiptAmount = getParameterValue(inputStream.nextLine(), "Amount: ");
		String receiptCompany = getParameterValue(inputStream.nextLine(), "Company: ");
		String receiptCountry = getParameterValue(inputStream.nextLine(), "Country: ");
		String receiptCity = getParameterValue(inputStream.nextLine(), "City: ");
		String receiptStreet = getParameterValue(inputStream.nextLine(), "Street: ");
		String receiptNumber = getParameterValue(inputStream.nextLine(), "Number: ");
		
		return new Receipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);		
	}

	@Override
	protected boolean skipLine(String fileLine) {
		if (fileLine.indexOf("Receipts:") != -1) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean endOfFile(String fileLine) {
		return false;
	}
}
