package outputManagePackage;

public class InputFileUpdaterXML extends TemplateInputFileUpdater {

	@Override
	protected void writeOutputLine() {
		// TODO Auto-generated method stub
		outputStream.println();
	}

	@Override
	protected void writeOutputLine(String title) {
		// TODO Auto-generated method stub
		outputStream.println("<" +title+ ">");
	}

	@Override
	protected void writeOutputLine(String title, double value) {
		// TODO Auto-generated method stub
		
		if (value == 0.0)
			outputStream.println("<" +title+ ">");
		else if (value == 1.0)
			outputStream.println("</" +title+ ">");
		else
			outputStream.println("<" +title+ "> " + value + "</" + title + ">");
	}

	@Override
	protected void writeOutputLine(String title, String value) {
		// TODO Auto-generated method stub
		outputStream.println("<" +title+ "> " + value + "</" + title + ">");
	}

}
