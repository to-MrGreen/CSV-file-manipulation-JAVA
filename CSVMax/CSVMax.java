
/**
 * Write a description of CSVMax here.
 * 
 * @author (Md.Samiul Haque) 
 * @version (1.0)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }
    
    //******************************************************
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coolestSoFar = null;
        double bug = -9999;
        for (CSVRecord currentRow : parser) {
            if (coolestSoFar == null) {
                coolestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coolestSoFar.get("TemperatureF"));
                if (currentTemp < lowestTemp && currentTemp != bug) {
                    coolestSoFar = currentRow;
                }
            }
        }
        return coolestSoFar;
    }
    
    public String coldestInFile() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coolestDaySoFar = null;
        double bug = -9999;
        String largFile = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coolestDaySoFar == null) {
                coolestDaySoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coolestDaySoFar.get("TemperatureF"));
                if (currentTemp < lowestTemp && currentTemp != bug) {
                    coolestDaySoFar = currentRow;
                    largFile = f.getPath();
                }
            }
        }
        return largFile;
    }
    
    public CSVRecord coldestInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coolestDaySoFar = null;
        double bug = -9999;
        //String largFile = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coolestDaySoFar == null) {
                coolestDaySoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coolestDaySoFar.get("TemperatureF"));
                if (currentTemp < lowestTemp && currentTemp != bug) {
                    coolestDaySoFar = currentRow;
                    //largFile = f;
                }
            }
        }
        return coolestDaySoFar;
    }
    
    public static CSVRecord lowestHumidityInFile(CSVParser parser) {
		//start with lowestSoFar as nothing
		CSVRecord lowestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
		}
		//The smallestSoFar is the answer
		return lowestSoFar;
	}
	public static CSVRecord getSmallestHumidityOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If smallestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
		    if (currentRow.get("Humidity").length() != 3){
			    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
			    double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
			    //Check if currentRow’s temperature < smallestSoFar’s
			    if (currentTemp < smallestTemp && currentTemp != -9999) {
				    //If so update smallestSoFar to currentRow
				    smallestSoFar = currentRow;
			    }
		    }
        }
		return smallestSoFar;
	}
	
     public static void testlowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
		System.out.println("Lowest humidity was " + smallest.get("Humidity") +
				   " at " + smallest.get("DateUTC"));
    }  
    
    public static CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
		}
		//The largestSoFar is the answer
		return lowestSoFar;
	}
	
	public static void testLowestHumidityInManyFiles() {
		CSVRecord csv = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
	}
	
	public static double averageTemperatureInFile(CSVParser parser){
		double sum=0;
		double avarage =0;
		int count = 1;
		for (CSVRecord currentRow : parser) {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			sum += currentTemp;
			avarage =sum/count;
			count++;
		}
		return avarage;
	}
	
    public static void  testAverageTemperatureInFile() {
	    FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
	    double avarage = averageTemperatureInFile(parser);
	    System.out.println("Average temperature in file is " + avarage);
	}
	
	public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value ){
        
        double sum=0;
	    double avarage =0;
	    int count = 1;
	    for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(currentHumidity>=value){
                sum += currentTemp;
                avarage =sum/count;
                count++;
            }
        }
	   
        return avarage;
    }

    
    public static void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
	    double avarage = averageTemperatureWithHighHumidityInFile(parser,80);
	    if(avarage==0)System.out.println("No temperatures with that humidity");
	    else System.out.println("Average temperature when high Humidity is " + avarage);
    }

	
    
    //***************************************************
    public void testerFile() {
        System.out.println(coldestInFile());
    }
    public void testerColdestInManyDays() {
        CSVRecord lowest = coldestInManyDays();
        System.out.println("Coldest temperature was "+lowest.get("TemperatureF")+" at "+lowest.get("DateUTC"));
    }
    
    public void testerColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was "+lowest.get("TemperatureF")+" at "+lowest.get("DateUTC"));
    }
    
    //********************************************************
    
    public void testerHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was "+largest.get("TemperatureF")+" at "+largest.get("DateUTC"));
    }
    
    public void testerHotestHurInFile() {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was "+largest.get("TemperatureF")+" at "+largest.get("TimeEST"));
    }
}
