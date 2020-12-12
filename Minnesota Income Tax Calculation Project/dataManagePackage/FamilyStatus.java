package dataManagePackage;

public class FamilyStatus {
    private static final FamilyStatus SINGLE
    	= new FamilyStatus("SINGLE");
    private static final FamilyStatus HEAD_OF_HOUSEHOLD 
    	= new FamilyStatus("HEAD OF HOUSEHOLD");
    private static final FamilyStatus MARRIED_FILLING_JOINTLY
    	= new FamilyStatus("MARRIED FILLING JOINTLY");
    private static final FamilyStatus MARRIED_FILLING_SEPERATELY
    	= new FamilyStatus("MARRIED FILLING SEPERATELY");
    
    private double[] incomeLimits;
    private double[][] incomeTaxRates;
    private String status;

    public FamilyStatus(String familyType){
    	switch(familyType){
        case "SINGLE":
            this.status = "SINGLE";
            this.incomeLimits = new double[] {36080, 90000, 143350, 254240};
            this.incomeTaxRates = new double[][] {
            	{1930.28, 0.705},
                {5731.64, 0.705},
                {9492.82, 0.785},
                {18197.69, 0.985}
            };
        case "HEAD OF HOUSEHOLD":
            this.status = "HEAD OF HOUSEHOLD";
        	this.incomeLimits = new double[] {18040, 71680, 90000, 127120};
        	this.incomeTaxRates = new double[][] {
        			{965.14, 0.705},
                    {4746.76, 0.785},
                    {6184.88, 0.785},
                    {9098.80, 0.985}
        	};
        case "MARRIED FILLING JOINTLY":
        	this.status = "MARRIED FILLING JOINTLY";
        	this.incomeLimits = new double[] {24680, 81080, 90000, 152540};
        	this.incomeTaxRates = new double[][] {
        		{1320.38, 0.705},
                {5296.58, 0,785},
                {5996.80, 0.785},
                {10906.19, 0.985}
        	};
        default: /*MARRIED FILLING SEPERATELY*/
            this.status = "MARRIED FILLING SEPERATELY";
        	this.incomeLimits = new double[] {30390, 90000, 122110, 203390};
        	this.incomeTaxRates = new double[][] {
        		{1625.87, 0.705},
                {5828.38, 0.705},
                {8092.13, 0.785},
                {14472.61, 0.985}
        	};
    	}
    }

    public static FamilyStatus getFamilyStatus(String familyType){
        switch(familyType){
            case "SINGLE":
                return SINGLE;
            case "HEAD OF HOUSEHOLD":
                return HEAD_OF_HOUSEHOLD;
            case "MARRIED FILLING JOINTLY":
                return MARRIED_FILLING_JOINTLY;
            default:
                return MARRIED_FILLING_SEPERATELY;
        }
    }

	public double[] getIncomeLimits() {
		return incomeLimits;
	}

	public double[][] getIncomeTaxRates() {
		return incomeTaxRates;
	}
	
	public String toString() {
		return status;
	}
}
