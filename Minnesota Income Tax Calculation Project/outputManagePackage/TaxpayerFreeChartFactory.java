package outputManagePackage;

public class TaxpayerFreeChartFactory {

	public TaxpayerFreeChart create(String type) {
		
		switch(type) {
			case "receipts":
				return new TaxpayerFreeChartPIE();
			case "taxAnalysis":
				return new TaxpayerFreeChartBAR();
			default:
				System.out.println(type + ": Wrong argument TaxpayerFreeChartFactory");
				return null;

		}
	}
	
}
