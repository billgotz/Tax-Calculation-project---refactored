package dataManagePackage;

import java.math.BigDecimal;

public class Receipt {
	protected String kind;
	protected String id;
	protected String date;
	protected double amount;
	protected Company company;
	
	public Receipt(String kind, String id, String date, String amount, String companyName, String country, String city, String street, String number){
		this.id = id;
		this.date = date;
		this.amount = Double.parseDouble(amount);
		this.company = new Company(companyName, country, city, street, number);
		this.kind = kind;
	}
	
	public String getId(){
		return id;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getKind(){
		return kind;
	}
	
	public double getAmount(){
		return (new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public Company getCompany(){
		return company;
	}
	
	public String toString(){
		return "ID: "+id
				+"\nDate: "+date
				+"\nKind: "+kind
				+"\nAmount: "+String.format("%.2f", amount)
				+"\nCompany: "+company.getName()
				+"\nCountry: "+company.getCountry()
				+"\nCity: "+company.getCity()
				+"\nStreet: "+company.getStreet()
				+"\nNumber: "+company.getNumber();
	}
}