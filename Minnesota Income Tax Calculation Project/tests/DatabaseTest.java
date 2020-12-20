package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import dataManagePackage.Database;
import dataManagePackage.Receipt;
public class DatabaseTest {

	private Database database;
		
	@Before 
	public void setFiles() {
		database = Database.getDatabase();
		database.setTaxpayersInfoFilesPath("../Minnesota Income Tax Calculation Project/inputFiles/");
		List<String> afmInfoFilesListToLoad = new ArrayList<String>();
		
		afmInfoFilesListToLoad.add("130456093_INFO.txt");
		
		database.proccessTaxpayersDataFromFilesIntoDatabase(database.getTaxpayersInfoFilesPath(), afmInfoFilesListToLoad);
	}
	
	
	
	@Test
	public void testUpdateTaxpayerInputFile() throws FileNotFoundException {
		
		Receipt newReceipt =  new Receipt("Basic", "6", "10/10/1000", "5424", "TestCompany", "TestCountry", 
											"TestCity", "TestStreet", "12");
		
		database.getTaxpayerFromArrayList(0).addReceiptToList(newReceipt);
		database.updateTaxpayerInputFile(0);
		
		File inFile = new File(database.getTaxpayersInfoFilesPath()+"/130456093_INFO.txt");
		Scanner scanner = new Scanner(inFile);
				
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if (line.contains("Receipt ID: 6"))
				assertForNextLines(scanner);
		}
		
		
		
	}



	private void assertForNextLines(Scanner scanner) {
		ArrayList<String> lines = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}
	
		assertEquals("Date: 10/10/1000", lines.get(0));
		assertEquals("Kind: Basic", lines.get(1));
		assertEquals("Amount: 5424.0", lines.get(2));
		assertEquals("Company: TestCompany", lines.get(3));
		assertEquals("Country: TestCountry", lines.get(4));
		assertEquals("City: TestCity", lines.get(5));
		assertEquals("Street: TestStreet", lines.get(6));
		assertEquals("Number: 12", lines.get(7));
	}

}
