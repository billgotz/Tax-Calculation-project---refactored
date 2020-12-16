package outputManagePackage;

public class TaxPayerInfoWriterXML extends TemplateTaxPayerInfoWriter {

	@Override
	protected void writeOutputLine(String title, double value) {
		// TODO Auto-generated method stub
		outputStream.println("<" +title+ "> " + value + "</" + title + ">");
	}

	@Override
	protected void writeOutputLine(String title, String value) {
		// TODO Auto-generated method stub
		outputStream.println("<" +title+ "> " + value + "</" + title + ">");
	}

}
