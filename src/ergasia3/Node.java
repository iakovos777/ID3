//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.util.ArrayList;

public class Node {
	private Attribute splitAttribute;
	private String splitValue;
	private int nodeNumber;
	private ArrayList<Instance> InstanceList;
	private String classification;
	private ArrayList<String> outputValues;
	private boolean isLeaf = true;
	private boolean toBeDeleted = false;
	private static int id=0;
	private int iD;
	
	/**
	 * COnstructor that creates a Node object and sets its classification value
	 * and node type (leaf or non-leaf)
	 * @param ex
	 * @param out
	 */
	public Node( ArrayList<Instance> ex, ArrayList<String> out, int number)
	{
		
		InstanceList = ex;
		outputValues = out;
		nodeNumber = number;
		setNodeType();
		setClassification();
		this.iD=id++;
	}
	
	/**
	 * Prunes this node by setting its classification and the boolean
	 * value that signifies it is a leaf node
	 */
	public void prune()
	{
		isLeaf = true;
		classification = getMajorityClassification();
		toBeDeleted = false;
		
	}
	
	/**
	 * Returns the node number of this node
	 * @return int nodeNumber
	 */
	public int getNumber()
	{
		return nodeNumber;
	}
	
	
	/**
	 * Automatically sets classification if this is a leaf node
	 *
	 */
	private void setClassification()
	{
		if(InstanceList.isEmpty())
		{
			classification = getMajorityClassification();
			return;
		}
		
		if(isLeaf)
			//System.out.println("size: "+InstanceList.size());
			classification = InstanceList.get(0).getClassification();
	}
	
	/**
	 * Marks a node to be deleted
	 *
	 */
	public void setToBeDeleted()
	{
		toBeDeleted = true;
	}
	
	/**
	 * Returns a boolean value representing whether this node is marked to
	 * be deleted or not
	 * @return
	 */
	public boolean getDeleteStatus()
	{
		return toBeDeleted;
	}
	
	
	/**
	 * Allows user to set the classification values of this node
	 * @param s classification value
	 */
	public void setClassification(String s)
	{
		classification = s;
	}
	
	/**
	 * Returns the classification of this node
	 * @return
	 */
	public String getClassification()
	{
		return classification;
	}
	
	
	/**
	 * Returns the split value for the Attribute contained within this node
	 * @return
	 */
	public String getSplitValue()
	{
		return splitValue;
	}
	
	/**
	 * Returns the Attribute contained within this node
	 * @return
	 */
	public Attribute getAttribute()
	{
		return splitAttribute;
	}
	
	/**
	 * Sets the boolean variable that tells whether or not this is 
	 * a leaf node
	 *
	 */
	private void setNodeType()
	{
		if(InstanceList.isEmpty())
		{
			isLeaf = true;
		}
		
		else
		{	
			String classValue = InstanceList.get(0).getClassification();
			for(Instance e: InstanceList)
			{
				if(!e.getClassification().equalsIgnoreCase(classValue))
					isLeaf = false;
			}
		}
	}
	
	/**
	 * Returns a boolean value that tells if this is a leaf node or not
	 * @return
	 */
	public boolean isLeaf()
	{
		return isLeaf;
	}
	
	
	
	/**
	 * Method that allows the user to set the split Attribute for this node
	 * @param f split Attribute
	 */
	public void setAttribute(Attribute f)
	{
		splitAttribute = f;
	}
	
	/**
	 * Method that allows the user to set the split Attribute value for the
	 * Attribute contained within this node
	 * @param val
	 */
	public void setAttributeValue(String val)
	{
			splitValue = val;
	}
	
	/**
	 * Method that returns the Instances in this node
	 * @return
	 */
	public ArrayList<Instance> getInstances()
	{
		return InstanceList;
	}
	
	/**
	 * Determines the majority classification for the Instances within
	 * this node
	 * @return classification
	 */
	public String getMajorityClassification()
	{
		int s =outputValues.size();
		int category[] = new int[s];
		
		
		for(int i=0; i<outputValues.size();i++){
			for(Instance e: InstanceList)
			{
				if(e.getClassification().equalsIgnoreCase(outputValues.get(i)))
					category[i]++;
			}
			
		}
		int max = category[0];
		int ind = 0;
		for(int i=0; i<outputValues.size();i++){
			if(category[i]>max){
				max= category[i];
				ind=i;
			}
		}
		return outputValues.get(ind);

	}
	public int getId(){
		return this.iD;
	}
}
