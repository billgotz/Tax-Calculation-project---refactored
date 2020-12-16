package outputManagePackage;

public class TaxPayerInfoWriterTXT extends TemplateTaxPayerInfoWriter {

	@Override
	protected void writeOutputLine(String title, double value) {
		// TODO Auto-generated method stub
		outputStream.println(title+ ": " + value);
	}

	@Override
	protected void writeOutputLine(String title, String value) {
		// TODO Auto-generated method stub
		outputStream.println(title+ ": " + value);
	}

}
