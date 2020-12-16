package inputManagePackage;

import java.util.Scanner;
import dataManagePackage.Receipt;
import dataManagePackage.Taxpayer;

public class ParseXML extends Parse{

	public ParseXML(String folderPath, String file) {
		loadTaxPayerData(folderPath, file);
	}

	public String getParameterValue(String fileLine, String parameterStartField, String parameterEndField){
		return fileLine.substring(parameterStartField.length(), fileLine.length()-parameterEndField.length());
	}

	@Override
	protected Taxpayer addTaxpayer(Scanner inputStream) {
		String taxpayerName = getParameterValue(inputStream.nextLine(), "<Name> ", " </Name>");
		String taxpayerAFM = getParameterValue(inputStream.nextLine(), "<AFM> ", " </AFM>");
		String taxpayerStatus = getParameterValue(inputStream.nextLine(), "<Status> ", " </Status>");
		String taxpayerIncome = getParameterValue(inputStream.nextLine(), "<Income> ", " </Income>");
		
		return new Taxpayer(taxpayerName, taxpayerAFM, taxpayerIncome, taxpayerStatus);		
	}

	@Override
	protected Receipt addReceipt(Scanner inputStream, String fileLine) {
		String receiptID = getParameterValue(fileLine, "<ReceiptID> ", " </ReceiptID>");
		String receiptDate = getParameterValue(inputStream.nextLine(), "<Date> ", " </Date>");
		String receiptKind = getParameterValue(inputStream.nextLine(), "<Kind> ", " </Kind>");
		String receiptAmount = getParameterValue(inputStream.nextLine(), "<Amount> ", " </Amount>");
		String receiptCompany = getParameterValue(inputStream.nextLine(), "<Company> ", " </Company>");
		String receiptCountry = getParameterValue(inputStream.nextLine(), "<Country> ", " </Country>");
		String receiptCity = getParameterValue(inputStream.nextLine(), "<City> ", " </City>");
		String receiptStreet = getParameterValue(inputStream.nextLine(), "<Street> ", " </Street>");
		String receiptNumber = getParameterValue(inputStream.nextLine(), "<Number> ", " </Number>");
		
		return new Receipt(receiptKind, receiptID, receiptDate, receiptAmount, receiptCompany, receiptCountry, receiptCity, receiptStreet, receiptNumber);		
	}

	@Override
	protected boolean skipLine(String fileLine) {
		if (fileLine.indexOf("<Receipts>") != -1) return true;
		return false;
	}

	@Override
	protected boolean endOfFile(String fileLine) {
		if (fileLine.indexOf("</Receipts>") != -1) return true;
		return false;
	}
	
	
}
