package outputManagePackage;
import dataManagePackage.*;
import dataManagePackage.Receipt.Receipt;
import java.awt.Dialog.ModalExclusionType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class OutputSystem {
	
	private static OutputSystem outputSystemInstance;
	
	private TaxPayerInfoWriterFactory infoWriterFactory;
	
	private static DefaultPieDataset receiptPieChartDataset;
	private static JFreeChart receiptPieJFreeChart;
	private static PiePlot piePlot;
	
	private static Database database = Database.getDatabase();
	
	private OutputSystem() {
		infoWriterFactory = new TaxPayerInfoWriterFactory();
		
	}
	
	public static OutputSystem getOutputSystem() {
		if (outputSystemInstance == null)
			outputSystemInstance = new OutputSystem();
		
		return outputSystemInstance;
	}
	
	
	public static DefaultPieDataset getReceiptPieChartDataset() {
		return receiptPieChartDataset;
	}

	public static JFreeChart getReceiptPieJFreeChart() {
		return receiptPieJFreeChart;
	}

	public static PiePlot getPiePlot() {
		return piePlot;
	}

	public static ChartFrame getReceiptPieChartFrame() {
		return receiptPieChartFrame;
	}

	private static ChartFrame receiptPieChartFrame;

	public static void saveUpdatedTaxpayerTxtInputFile(String filePath, int taxpayerIndex){
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+filePath);
		}
		
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		outputStream.println("Name: "+taxpayer.getName());
		outputStream.println("AFM: "+taxpayer.getAFM());
		outputStream.println("Status: "+taxpayer.getFamilyStatus());
		outputStream.println("Income: "+taxpayer.getIncome());
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println("Receipts:");
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println("Receipt ID: "+receipt.getId());
				outputStream.println("Date: "+receipt.getDate());
				outputStream.println("Kind: "+receipt.getKind());
				outputStream.println("Amount: "+receipt.getAmount());
				outputStream.println("Company: "+receipt.getCompany().getName());
				outputStream.println("Country: "+receipt.getCompany().getCountry());
				outputStream.println("City: "+receipt.getCompany().getCity());
				outputStream.println("Street: "+receipt.getCompany().getStreet());
				outputStream.println("Number: "+receipt.getCompany().getNumber());
				outputStream.println();
			}
		}
		
		outputStream.close();
	}
	
	public static void saveUpdatedTaxpayerXmlInputFile(String filePath, int taxpayerIndex){
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+filePath);
		}
		
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		outputStream.println("<Name> "+taxpayer.getName()+" </Name>");
		outputStream.println("<AFM> "+taxpayer.getAFM()+" </AFM>");
		outputStream.println("<Status> "+taxpayer.getFamilyStatus()+" </Status>");
		outputStream.println("<Income> "+taxpayer.getIncome()+" </Income>");
		
		if (taxpayer.getReceiptsArrayList().size() > 0){
			outputStream.println();
			outputStream.println("<Receipts>");
			outputStream.println();
			
			for (Receipt receipt : taxpayer.getReceiptsArrayList()){
				outputStream.println("<ReceiptID> "+receipt.getId()+" </ReceiptID>");
				outputStream.println("<Date> "+receipt.getDate()+" </Date>");
				outputStream.println("<Kind> "+receipt.getKind()+" </Kind>");
				outputStream.println("<Amount> "+receipt.getAmount()+" </Amount>");
				outputStream.println("<Company> "+receipt.getCompany().getName()+" </Company>");
				outputStream.println("<Country> "+receipt.getCompany().getCountry()+" </Country>");
				outputStream.println("<City> "+receipt.getCompany().getCity()+" </City>");
				outputStream.println("<Street> "+receipt.getCompany().getStreet()+" </Street>");
				outputStream.println("<Number> "+receipt.getCompany().getNumber()+" </Number>");
				outputStream.println();
			}
			
			outputStream.println("</Receipts>");
		}
		
		outputStream.close();
	}
	
	public void saveTaxPayerInfoToOutputFile(String extension, String folderSavePath, int taxPayerIndex) {
		infoWriterFactory.create(extension).saveTaxPayerInfo(folderSavePath, taxPayerIndex);
	}
	
	public static void createTaxpayerReceiptsPieJFreeChart(int taxpayerIndex){
		receiptPieChartDataset = new DefaultPieDataset();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		
		receiptPieChartDataset.setValue("Basic", taxpayer.getBasicReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Entertainment", taxpayer.getEntertainmentReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Travel", taxpayer.getTravelReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Health", taxpayer.getHealthReceiptsTotalAmount());
		receiptPieChartDataset.setValue("Other", taxpayer.getOtherReceiptsTotalAmount());
		
		receiptPieJFreeChart = ChartFactory.createPieChart("Receipt Pie Chart", receiptPieChartDataset);
		piePlot = (PiePlot)receiptPieJFreeChart.getPlot();
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0}: {1}$ ({2})", new DecimalFormat("0.00"), new DecimalFormat("0.00%"));
		piePlot.setLabelGenerator(generator); 

		receiptPieChartFrame = new ChartFrame(database.getTaxpayerNameAfmValuesPairList(taxpayerIndex), receiptPieJFreeChart);
		receiptPieChartFrame.pack();
		receiptPieChartFrame.setResizable(false);
		receiptPieChartFrame.setLocationRelativeTo(null);
		receiptPieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		receiptPieChartFrame.setVisible(true);
	}
	
	public static void createTaxpayerTaxAnalysisBarJFreeChart(int taxpayerIndex){
		DefaultCategoryDataset taxAnalysisBarChartDataset = new DefaultCategoryDataset();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		
		String taxVariationType = taxpayer.getTaxInxrease()!=0? "Tax Increase" : "Tax Decrease";
		double taxVariationAmount = taxpayer.getTaxInxrease()!=0? taxpayer.getTaxInxrease() : taxpayer.getTaxDecrease()*(-1);
		
		taxAnalysisBarChartDataset.setValue(taxpayer.getBasicTax(), "Tax", "Basic Tax");
		taxAnalysisBarChartDataset.setValue(taxVariationAmount, "Tax", taxVariationType);
		taxAnalysisBarChartDataset.setValue(taxpayer.getTotalTax(), "Tax", "Total Tax");

		JFreeChart taxAnalysisJFreeChart = ChartFactory.createBarChart("Tax Analysis Bar Chart", "",  "Tax Analysis in $", taxAnalysisBarChartDataset, PlotOrientation.VERTICAL, true, true, false);

		ChartFrame receiptPieChartFrame = new ChartFrame(database.getTaxpayerNameAfmValuesPairList(taxpayerIndex), taxAnalysisJFreeChart);
		receiptPieChartFrame.pack();
		receiptPieChartFrame.setResizable(false);
		receiptPieChartFrame.setLocationRelativeTo(null);
		receiptPieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		receiptPieChartFrame.setVisible(true);
	}
}
