package outputManagePackage;

import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class TaxpayerFreeChartPIE extends TemplateTaxpayerFreeChart {

	@Override
	protected ChartFrame createChartFrame(int taxpayerIndex) {
		
		DefaultPieDataset receiptPieChartDataset = new DefaultPieDataset();		
		
		receiptPieChartDataset.setValue("Basic", taxpayer.getReceiptAmount("Basic"));
		receiptPieChartDataset.setValue("Entertainment", taxpayer.getReceiptAmount("Entertainment"));
		receiptPieChartDataset.setValue("Travel", taxpayer.getReceiptAmount("Travel"));
		receiptPieChartDataset.setValue("Health", taxpayer.getReceiptAmount("Health"));
		receiptPieChartDataset.setValue("Other", taxpayer.getReceiptAmount("Other"));
		
		JFreeChart receiptPieJFreeChart = ChartFactory.createPieChart("Receipt Pie Chart", receiptPieChartDataset);
		PiePlot piePlot = (PiePlot)receiptPieJFreeChart.getPlot();
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0}: {1}$ ({2})", new DecimalFormat("0.00"), new DecimalFormat("0.00%"));
		piePlot.setLabelGenerator(generator); 
		return new ChartFrame(database.getTaxpayerNameAfmValuesPairList(taxpayerIndex), receiptPieJFreeChart);
	}


}
