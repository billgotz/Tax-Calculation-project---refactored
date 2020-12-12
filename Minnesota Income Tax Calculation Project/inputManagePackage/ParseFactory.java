package inputManagePackage;

public class ParseFactory {

	public static Parse createParser(String fileExtension, String folderPath, String files) {
		if (fileExtension.equals("txt")) {
			return new ParseTXT(folderPath, files);
		} else if (fileExtension.equals("xml")) {
			return new ParseXML(folderPath, files);
			
		} else {
			return null;
		}
	}
}
