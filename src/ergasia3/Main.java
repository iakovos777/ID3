//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.io.File;



public class Main {
	
	private static File currentDirectory = new File(new File(".").getAbsolutePath());
	private static String currentDirectoryPath = currentDirectory.getAbsolutePath()
												.substring(0,currentDirectory.getAbsolutePath().length() - 1);
	/* txt Files Base Path */
	private static String basePath =  currentDirectoryPath + "\\DataSets\\";
	private static String attributePath =  currentDirectoryPath + "\\Attributes\\";
	
	/*  Path for each file */
	private static String attributeFile1 =  attributePath + "Attributes.txt";
	private static String trainSetFile1 = basePath + "TrainingSets.txt";
	private static String testSetFile1 = basePath + "TestSets.txt";
	
	private static String attributeFile2 =  attributePath + "Attributes-Iris.txt";
	private static String trainSetFile2 = basePath + "Data-Iris.txt";
	private static String testSetFile2 = basePath + "Test-Iris.txt";
	public static void main(String[] args)  
    {
		System.out.println("CARS!!!");
		ID3 id3 = new ID3();
		
		id3.run_id3(attributeFile1, trainSetFile1, testSetFile1);
		
		System.out.println("IRIS!!!");
			
		id3.run_id3(attributeFile2, trainSetFile2, testSetFile2);
    
    }
}
