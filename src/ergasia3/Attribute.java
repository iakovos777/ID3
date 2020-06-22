//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.util.ArrayList;
import java.util.Collections;

public class Attribute {
	private String name;
	private ArrayList<String> possibleValues = new ArrayList<String>();
	private String commaDelimitedValues;
	private int index;
	private boolean continuous;

	
	/**
	 * Constructor for creating an object of type Attribute
	 * @param AttributeName name of the Attribute
	 * @param AttributeType type of Attribute 
	 * @param values legal values the Attribute can assume
	 */
	public Attribute(String AttributeName,  String values, String continuous)
	{
		name = AttributeName;
		commaDelimitedValues = values;
		parseValues(values);
		Continuous(continuous);
	}
	
	public Attribute(String AttributeName,  String values)
	{
		name = AttributeName;
		commaDelimitedValues = values;
		parseValues(values);
		this.continuous=false;
	}

	private void Continuous(String continuous) {
		
		if(continuous.equalsIgnoreCase(continuous)){
			this.continuous=true;
		}
		else
			this.continuous=false;
	}
	
	public boolean isContinuous(){
		return this.continuous;
	}

	/**
	 * Determines if a given Attribute is equal to this Attribute
	 * @param f
	 * @return
	 */
	public boolean same(Attribute f)
	{
		if(f.getAttributeName().equalsIgnoreCase(name)&& f.getPossibleValues().equals(possibleValues))
			return true;
		else
			return false;
	}
	
	/**
	 * Sets the index of this Attribute within each example's list of values
	 * @param i
	 */
	public void setIndex(int i)
	{
		index = i;
	}
	
	/**
	 * Returns the index of this Attribute
	 * @return
	 */
	public int getIndex()
	{
		return index;
	}
	
	

	
	
	/**
	 * Parses the possible Attribute values 
	 * @param vals -comma delimited values read from the file
	 */
	private  void parseValues(String vals)
	{
		vals = vals.trim();
		String tmp[] = vals.split(",");
			for(int i = 0; i < tmp.length; i++)
			{
				
				possibleValues.add(tmp[i]);
			}
			
			
	}
	 private void removePossibleValues(ArrayList<String> values){
		 for(int i=0; i<this.possibleValues.size(); i++){
				this.possibleValues.remove(i);
			}
	 }
	
	
	
	
	
	
	public void setContinuousValues(ArrayList<String> unsorted ){
		Collections.sort(unsorted);		
		if(unsorted.size()>20){
			removePossibleValues(this.possibleValues);
			int x = unsorted.size()/10;
			int div = unsorted.size()%10;
			if(div>5){
				int i=0;
				
				while((i<x)&&(x<=unsorted.size())){
					String s = unsorted.get(i)+"-"+unsorted.get(x-1);
					this.possibleValues.add(s);
					i=x;
					x+=x;
					
				}
				String s = unsorted.get(i)+"-"+unsorted.get(unsorted.size()-1);
				this.possibleValues.add(s);
			}
			else if(div==0){
				int i=0;
				while((i<x)&&(x<=unsorted.size())){
					String s = unsorted.get(i)+"-"+unsorted.get(x-1);
					this.possibleValues.add(s);
					i=x;
					x+=x;
				}
				
			}
			else{
				int i=0;
				while((i<x)&&(x<=unsorted.size()-x)){
					String s = unsorted.get(i)+"-"+unsorted.get(x-1);
					this.possibleValues.add(s);
					i=x;
					x+=x;
				}
				String s = unsorted.get(i)+"-"+unsorted.get(unsorted.size()-1);
				this.possibleValues.add(s);
			}
		}
		
	}
	
	/**
	 * Checks the validity of a Attribute value
	 * @param val -value to test
	 * @return -returns false if an illegal value was given
	 */
	public boolean correctValue(String val)
	{
		
		
			boolean result = false;
			int i = 0;
			while(!result && i < possibleValues.size())
			{
				if(possibleValues.get(i).equalsIgnoreCase(val.trim()))
					result = true;
				i++;
			}
			return result;
		
		
		
	}
	
	
	
	public String getAttributeName()
	{
		return name;
	}
	
	
	/**
	 * Returns an arraylist of possible values
	 * @return
	 */
	public ArrayList<String> getPossibleValues()
	{
		return possibleValues;
	}
	
	/**
	 * Return a long string of the possible values
	 * @return
	 */
	public String getValuesInPrintFormat()
	{
		return name + " " +  commaDelimitedValues;
}

}
