//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.util.ArrayList;

public class Instance {
	
	private ArrayList<String> values = new ArrayList<String>();
	private String valsInPrintFormat;
	private String classification;
	
	public Instance(String line, int outputIndex)
	{
		valsInPrintFormat = line;
		parseValues(line, outputIndex);
	}
	
	private  void parseValues(String vals, int outputIndex)
	{
		String tmp[] = vals.split(",");
		for(int i = 0; i < tmp.length; i++)
		{
			values.add(tmp[i].trim());
		}
		classification = values.get(outputIndex).trim();
	}
	
	
	
	public String getClassification()
	{
		return classification;
	}
	
	public void printInstance()
	{
		System.out.println(valsInPrintFormat);
	}
	
	/**
	 * Returns an ArrayList of feature values in String format
	 * @return
	 */
	public ArrayList<String> getValues()
	{
		return values;
}
}
