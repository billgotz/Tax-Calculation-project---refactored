package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dataManagePackage.Receipt;
import dataManagePackage.Taxpayer;

public class TaxpayerTest {

	private Taxpayer testTaxpayer;
	
	@Before
	public void createTaxpayer() {

		testTaxpayer = new Taxpayer("Tester", "049642921", "1500.0", "SINGLE");
		
		Receipt rec1 =  new Receipt("Basic", "1", "10/10/1000", "5424", "TestCompany", "TestCountry", 
				"TestCity", "TestStreet", "12");
		
		Receipt rec2 =  new Receipt("Health", "2", "11/10/1000", "7986", "TestCompany", "TestCountry", 
				"TestCity", "TestStreet", "12");
		
		testTaxpayer.addReceiptToList(rec1);
		testTaxpayer.addReceiptToList(rec2);

	}
	
	@Test
	public void testTaxpayerBasicTax() {
		assertEquals(802.5, testTaxpayer.getBasicTax(), 0.0);
	}
	
	@Test
	public void testTaxpayerTaxIncrease() {
		assertEquals(0, testTaxpayer.getTaxIncrease(), 0.0);
	}
	
	@Test 
	public void testTaxpayerTaxDecrease() {
		assertEquals(240.75, testTaxpayer.getTaxDecrease(), 0.0);
	}
	
	@Test
	public void testTaxpayerTotalTax() {
		assertEquals(561.75, testTaxpayer.getTotalTax(), 0.0);
	}


}
