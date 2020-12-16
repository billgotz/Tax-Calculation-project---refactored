package outputManagePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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

	@Override
	protected PrintWriter createOutputStream(String folderSavePath) {
		try
		{
			PrintWriter outputStream = new PrintWriter(new FileOutputStream(folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt"));
			return outputStream;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+folderSavePath+"//"+taxpayer.getAFM()+"_LOG.txt");
		}
		return null;

	}

}
