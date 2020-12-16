package outputManagePackage;

public class OutputSystem {
	
	private static OutputSystem outputSystemInstance;
	
	private TaxPayerInfoWriterFactory infoWriterFactory;
	private InputFileUpdaterFactory inputFileUpdaterFactory;
	private TaxpayerFreeChartFactory taxpayerFreeChartFactory;
	
	private OutputSystem() {
		infoWriterFactory = new TaxPayerInfoWriterFactory();
		inputFileUpdaterFactory = new InputFileUpdaterFactory();
		taxpayerFreeChartFactory = new TaxpayerFreeChartFactory();
	}
	
	public static OutputSystem getOutputSystem() {
		if (outputSystemInstance == null)
			outputSystemInstance = new OutputSystem();
		
		return outputSystemInstance;
	}
		 
	public void saveUpdatedTaxpayerFromInput(String extension, String filePath, int taxPayerIndex) {
		inputFileUpdaterFactory.create(extension).saveUpdatedTaxPayer(filePath, taxPayerIndex);	
	}
	
	public void saveTaxPayerInfoToOutputFile(String extension, String folderSavePath, int taxPayerIndex) {
		infoWriterFactory.create(extension).saveTaxPayerInfo(folderSavePath, taxPayerIndex);
	}
	
	public void createTaxpayerChart(String type, int taxpayerIndex) {
		taxpayerFreeChartFactory.create(type).createJFreeChart(taxpayerIndex);
	}
	
}
