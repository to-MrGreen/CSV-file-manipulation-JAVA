
/**
 * Write a description of CountryInfo here.
 * 
 * @author (Md.Samiul Haque) 
 * @version (1.0)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class CountryInfo {
    public String cntryInfo(CSVParser parser,String country) {
        
        for (CSVRecord record : parser) {
            String fcountry = record.get("Country");
            if (fcountry.contains(country)) {
                String fexport = record.get("Exports");
                String fvalue = record.get("Value (dollars)");
                String found = fcountry+": "+fexport+": "+fvalue;
                System.out.println(found);
            }
        }
        return "Done :) vrsn 1.0";
    }
    
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2) {
        for (CSVRecord record : parser) {
            String fexport = record.get("Exports");
            if (fexport.contains(exportItem1) && fexport.contains(exportItem2)) {
                String fcountry = record.get("Country");
                System.out.println(fcountry);
            }
        }
        
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countryCount = 0;
        for (CSVRecord record : parser) {
            String fexport = record.get("Exports");
            if (fexport.contains(exportItem)) {
                String fcountry = record.get("Country");
                System.out.println(fcountry);
                countryCount = countryCount+1;
            }
        }
        return countryCount;
    }
    
    public void bigExporters(CSVParser parser,String value) {
        for (CSVRecord record : parser) {
            String fvalue = record.get("Value (dollars)");
            if (fvalue.length() > value.length()) {
                String fcountry = record.get("Country");
                String found = fcountry+" "+fvalue;
                System.out.println(found);
            }
        }
    }
    
    public void testerBigExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    
    public void testerNumOfExpoter(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"cocoa"));
    }
    
    public void testerListExpoter2Value() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
    }
    
    public void testerCountryInfo(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(cntryInfo(parser,"Nauru"));
        
    }
}
