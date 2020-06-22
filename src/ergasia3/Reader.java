//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	
	public Reader()
	{
		// do nothing
	}
	
	
	/**
	 * This method reads the names file and creates an ArrayList of
	 * type Feature; this is a list of all the features
	 * @param fileName -file containing the Instance names
	 * @return -an arraylist of features
	 */
	public ArrayList<Attribute> createAttributes(String fileName)
	{
		final int ATTRIBUTE_FIELDS = 2;
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		//Read the names file
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
		
			int index = 0;
			while((line = in.readLine())  != null) 
			{
				//Create features skipping the comment lines 
				//and blank lines
				 if(!line.startsWith("|") && (line.length() != 0))
				 {
					 @SuppressWarnings("resource")
					Scanner scanner = new Scanner(line);
					
					 //Each feature will have two fields: (name, values)
					 String[] tmp = new String[ATTRIBUTE_FIELDS];
					 
			         for(int i = 0; i < ATTRIBUTE_FIELDS; i++)
			         {
			        	 tmp[i] = scanner.next();
			        	
					 }
			         
			         
			         if(tmp.length==3){
			        	 Attribute current = new Attribute(tmp[0], tmp[1], tmp[2]);
			        	 current.setIndex(index);
				         attributes.add(current); 
						 index++;
			         }
			         else{
			        	 Attribute current = new Attribute(tmp[0], tmp[1]); 
			        	 current.setIndex(index);
				         attributes.add(current); 
						 index++;
			         }
			         
				 }
				
			}
			in.close();
		}
		
		//If file not found, catch the appropriate exception
		catch(FileNotFoundException e)
		{
			System.out.println("File " + fileName + " not found");
			System.exit(0);
		}
		
		catch(IOException e)
		{
			System.out.println("IO Exception!");
			System.exit(0);
		}
		return attributes;
	}
	
	/**
	 * This method reads from the Instances.data file and prints out illegal feature values.
	 * It also prints out the count of output values in category A and category B
	 * @param fileName -name of the Instances file
	 * @param features -array list of the features
	 * @param outputValues -arraylist of the possible output values
	 */
	public ArrayList<Instance> createInstanceList(String fileName, int outputIndex)
	{
		
		ArrayList<Instance> InstanceList = new ArrayList<Instance>();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
		
			String line;
			while((line = in.readLine())  != null ) 
			{
				
				 if(!line.startsWith("//") && (line.length() != 0))
				 {
					 InstanceList.add(new Instance(line, outputIndex));
				 }
				 
			}	 
			in.close();
			
			
		}
		
		//If file not found, catch the appropriate exception
		catch(FileNotFoundException e)
		{
			System.out.println("File " + fileName + " not found");
			System.exit(0);
		}
		
		catch(IOException e)
		{
			System.out.println("IO Exception!");
			System.exit(0);
		}
		
		return InstanceList;
					 
}

}
