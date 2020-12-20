package dataManagePackage;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Taxpayer {
	private String name;
	private String afm;
	private double income;
	private double basicTax;
	private double taxIncrease;
	private double taxDecrease;
	private double totalTax;
	private FamilyStatus familyStatus;
	private ArrayList<Receipt> receipts;
	
	private final double MIN_TAX_RATE = 0.535;

	public Taxpayer(String name, String afm, String income, String status){
		this.name = name;
		this.afm = afm;
		this.income = Double.parseDouble(income);
		this.familyStatus = FamilyStatus.getFamilyStatus(status);
		setBasicTaxBasedOnFamilyStatus();
		taxIncrease = 0;
		taxDecrease = 0;
		receipts = new ArrayList<Receipt>();
	}
	
	private void setBasicTaxBasedOnFamilyStatus(){
		basicTax = calculateTax(income);
	}
    
    public double calculateTax(double totalIncome){
        double tax;
        double[] incomeLimit = familyStatus.getIncomeLimits();
        double[][] rate = familyStatus.getIncomeTaxRates();

        if (totalIncome < incomeLimit[0]){
            tax = MIN_TAX_RATE * totalIncome;
        }
        else if (totalIncome < incomeLimit[1]){
            tax = rate[0][0] + ((rate[0][1] * (totalIncome - incomeLimit[0])));
        }
        else if (totalIncome < incomeLimit[2]){
            tax = rate[1][0] + ((rate[1][1] * (totalIncome - incomeLimit[1])));
        }
        else if (totalIncome < incomeLimit[3]){
            tax = rate[2][0] + ((rate[2][1] * (totalIncome - incomeLimit[2])));
        }
        else{
            tax = rate[3][0] + ((rate[3][1] * (totalIncome - incomeLimit[3])));
        }

        return tax;
    }
	
    
	public String toString(){
		return "Name: "+name
				+"\nAFM: "+afm
				+"\nStatus: "+familyStatus
				+"\nIncome: "+String.format("%.2f", income)
				+"\nBasicTax: "+String.format("%.2f", basicTax)
				+"\nTaxIncrease: "+String.format("%.2f", taxIncrease)
				+"\nTaxDecrease: "+String.format("%.2f", taxDecrease);
	}
	
	public Receipt getReceipt(int receiptID){
		return receipts.get(receiptID);
	}
	
	public ArrayList<Receipt> getReceiptsArrayList(){
		return receipts;
	}
	
	public String[] getReceiptsList(){
		String[] receiptsList = new String[receipts.size()];
		
		int c = 0;
		for (Receipt receipt : receipts){
			receiptsList[c++] = receipt.getId() + " | " + receipt.getDate() + " | " + receipt.getAmount();
		}
		
		return receiptsList;
	}

	
	public double getReceiptAmount(String receiptType){
        double totalAmount = 0;

        for (Receipt receipt : receipts){
            if(receipt.getKind().equals(receiptType)){
                totalAmount += receipt.getAmount();
            }
        }

        return (new BigDecimal(totalAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	
	public double getTotalReceiptsAmount(){
		double totalReceiptsAmount = 0;
		
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		return (new BigDecimal(totalReceiptsAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public String getName(){
		return name;
	}
	
	public String getAFM(){
		return afm;
	}

	public String getFamilyStatus() {
		return familyStatus.toString();
	}
	
	public double getIncome(){
		return (new BigDecimal(income).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getBasicTax(){
		return (new BigDecimal(basicTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxIncrease(){
		return (new BigDecimal(taxIncrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTaxDecrease(){
		return (new BigDecimal(taxDecrease).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public double getTotalTax(){
		return (new BigDecimal(totalTax).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public void addReceiptToList(Receipt receipt){
		receipts.add(receipt);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void removeReceiptFromList(int index){
		receipts.remove(index);
		
		calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts();
	}
	
	public void calculateTaxpayerTaxIncreaseOrDecreaseBasedOnReceipts(){
		double totalReceiptsAmount = 0;
		for (Receipt receipt : receipts){
			totalReceiptsAmount += receipt.getAmount();
		}
		
		taxIncrease = 0;
		taxDecrease = 0;
		if ((totalReceiptsAmount/(double)income) < 0.2){
			taxIncrease = basicTax * 0.08;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.4){
			taxIncrease = basicTax * 0.04;
		}
		else if ((totalReceiptsAmount/(double)income) < 0.6){
			taxDecrease = basicTax * 0.15;
		}
		else{
			taxDecrease = basicTax * 0.30;

		}
		
		totalTax = basicTax + taxIncrease - taxDecrease;
	}
}