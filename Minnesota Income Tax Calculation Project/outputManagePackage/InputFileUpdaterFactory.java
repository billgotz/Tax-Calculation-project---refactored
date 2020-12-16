package outputManagePackage;

public class InputFileUpdaterFactory {
	
	public InputFileUpdater create(String extension) {
		switch(extension) {
		case "txt":
			return new InputFileUpdaterTXT();
		case "xml":
			return new InputFileUpdaterXML();
		default:
			System.out.println(extension + ": Wrong argument InputFileUpdaterFactory");
			return null;

		}
	}

}
