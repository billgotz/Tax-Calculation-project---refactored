package outputManagePackage;

public class InputFileUpdaterTXT extends TemplateInputFileUpdater {

	@Override
	protected void writeOutputLine() {
		// TODO Auto-generated method stub
		outputStream.println();
	}
	
	@Override
	protected void writeOutputLine(String title) {
		// TODO Auto-generated method stub
		outputStream.println(title+ ": ");
	}

	@Override
	protected void writeOutputLine(String title, double value) {
		// TODO Auto-generated method stub
		
		if (value == 1.0)
			return;
		else
			outputStream.println(title+ ": " + value);
	}

	@Override
	protected void writeOutputLine(String title, String value) {
		// TODO Auto-generated method stub
		outputStream.println(title+ ": " + value);
	}

}
