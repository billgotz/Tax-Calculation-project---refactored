package outputManagePackage;

import java.awt.Dialog.ModalExclusionType;

import org.jfree.chart.ChartFrame;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public abstract class TemplateTaxpayerFreeChart implements TaxpayerFreeChart {
	
	protected Database database = Database.getDatabase();
	protected Taxpayer taxpayer;
	
	public void createJFreeChart(int taxpayerIndex) {
	
		taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		
		ChartFrame chartFrame = createChartFrame(taxpayerIndex);
		chartFrame.pack();
		chartFrame.setResizable(false);
		chartFrame.setLocationRelativeTo(null);
		chartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		chartFrame.setVisible(true);
				
	}

	protected abstract ChartFrame createChartFrame(int taxpayerIndex);
	

}
