package outputManagePackage;

public class TaxPayerInfoWriterFactory {

	
	public TaxPayerInfoWriter create(String extension) {
		switch(extension) {
			case "txt":
				return new TaxPayerInfoWriterTXT();
			case "xml":
				return new TaxPayerInfoWriterTXT();
			default:
				System.out.println(extension + ": Wrong argument TaxPayerInfoWriterFactory");
				return null;
	
		}
	}
}
