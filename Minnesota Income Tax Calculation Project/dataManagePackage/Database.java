package dataManagePackage;

import inputManagePackage.*;
import outputManagePackage.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Database {
	
	
	//Singleton instance field
	private static Database databaseInstance;
	
	private static String taxpayersInfoFilesPath;
	private static ArrayList<Taxpayer> taxpayersArrayList = new ArrayList<Taxpayer>();
	
	private OutputSystem outputSystem;
	
	private Database() {
		outputSystem = OutputSystem.getOutputSystem();
	}
	
	public static Database getDatabase() {
		if (databaseInstance == null)
			databaseInstance = new Database();
		
		return databaseInstance;
	}
	
	
	
	public void setTaxpayersInfoFilesPath(String taxpayersInfoFilesPath){
		Database.taxpayersInfoFilesPath = taxpayersInfoFilesPath;
	}
	
	public String getTaxpayersInfoFilesPath(){
		return Database.taxpayersInfoFilesPath;
	}
	
	public void proccessTaxpayersDataFromFilesIntoDatabase(String afmInfoFilesFolderPath, List<String> taxpayersAfmInfoFiles){
		
		for (String file : taxpayersAfmInfoFiles) {
			String extension = file.substring(file.lastIndexOf(".") + 1);
			ParseFactory.createParser(extension, afmInfoFilesFolderPath, file);
		}
	}
	
	public void addTaxpayerToList(Taxpayer taxpayer){
		taxpayersArrayList.add(taxpayer);
	}
	
	public int getTaxpayersArrayListSize(){
		return taxpayersArrayList.size();
	}
	
	public Taxpayer getTaxpayerFromArrayList(int index){
		return taxpayersArrayList.get(index);
	}
	
	public void removeTaxpayerFromArrayList(int index){
		taxpayersArrayList.remove(index);
	}
	
	public String getTaxpayerNameAfmValuesPairList(int index){
		Taxpayer taxpayer = taxpayersArrayList.get(index);
		return taxpayer.getName() + " | " + taxpayer.getAFM();
	}
	
	public String[] getTaxpayersNameAfmValuesPairList(){
		String[] taxpayersNameAfmValuesPairList = new String[taxpayersArrayList.size()];
		
		int c = 0;
		for (Taxpayer taxpayer : taxpayersArrayList){
			taxpayersNameAfmValuesPairList[c++] = taxpayer.getName() + " | " + taxpayer.getAFM();
		}
		
		return taxpayersNameAfmValuesPairList;
	}
	
	public void updateTaxpayerInputFile(int index){
		File taxpayersInfoFilesPathFileObject = new File(taxpayersInfoFilesPath);
		FilenameFilter fileNameFilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
               return (name.toLowerCase().endsWith("_info.txt") || name.toLowerCase().endsWith("_info.xml"));
            }
         };
		
		for (File file : taxpayersInfoFilesPathFileObject.listFiles(fileNameFilter)){
			if (!file.getName().contains(taxpayersArrayList.get(index).getAFM())) continue;
			
			if (file.getName().toLowerCase().endsWith(".txt")){
				outputSystem.saveUpdatedTaxpayerFromInput("txt", file.getAbsolutePath(), index);
			}
			if (file.getName().toLowerCase().endsWith(".xml")){
				outputSystem.saveUpdatedTaxpayerFromInput("xml", file.getAbsolutePath(), index);
			}
			break;
		}
	}	
}