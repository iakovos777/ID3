//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.util.ArrayList;

public class Utility {
	
	 
	
	public Utility(){
		
	}
	
	
	
	/**
	 * Determines the best split Attribute by calculating information gain
	 * @param totalEntropy entropy of the entire training set
	 * @param Instances list of Instances 
	 * @param AttributeList list of Attributes
	 * @return
	 */
	public static Attribute getBestAttribute(double totalEntropy, ArrayList<Instance> Instances, ArrayList<Attribute> AttributeList,  ArrayList<String> outputValues)
	{
		
		
		
		
			Attribute bestAttribute = AttributeList.get(0);
			double maxGain = getInformationGain(totalEntropy, AttributeList.get(0),
												Instances,outputValues);
			//System.out.println("max info gain of "+AttributeList.get(0).getAttributeName()+": "+maxGain);
			int size= AttributeList.size()-1;
			for(int i=0; i<size; i++)
			{
				//System.out.println(size);
				Attribute f = AttributeList.get(i);
				if(f.getAttributeName().equalsIgnoreCase("class")){
					continue;
				}
				double gain = getInformationGain(totalEntropy, f, Instances, outputValues);
				//System.out.println("info gain of "+f.getAttributeName()+": "+gain);
				if(gain > maxGain)
				{
					maxGain = gain;
					bestAttribute = f;
				}
			}
			//System.out.println("best info gain of "+bestAttribute.getAttributeName()+": "+maxGain);
			return bestAttribute;
		
			
			
		
	}
	
	/**
	 * Calculates the total entropy of a list of Instances
	 * @param Instances list of Instances
	 * @return values of the total entropy
	 */
	public static double getTotalEntropy(ArrayList<Instance> Instances, ArrayList<String> outputValues)
	{
		ArrayList<Double> categoryNums = new ArrayList<Double>();
		double totalEntropy = 0.0;
	
		for(int i=0; i<outputValues.size(); i++)
		{
		//Count the number of "positive" and "negative" Instances
			double category = 0.0;
			for(Instance e: Instances)
			{
				if(e.getClassification().equals(outputValues.get(i)))
					category++;
				
			}
			double term = category/Instances.size();
			categoryNums.add(term);
		}
		
		
		for(int i=0; i<categoryNums.size(); i++)
		{
			//System.out.println(i+" term: "+categoryNums.get(i));
			double clas = categoryNums.get(i);
			//Calculate total entropy
			if(clas==0)
				totalEntropy  +=0;
			else
				totalEntropy  += -clas*(Math.log(clas)/Math.log(2));
		}
		
		//System.out.println("totalEntropy: "+totalEntropy);
		return totalEntropy;
		
	}

	
	
	/**
	 * Returns the information gain of a Attribute
	 * @param totalEntropy total entropy of the training set 
	 * @param f current Attribute to split on
	 * @param Instances list of training Instances
	 * @param contAttributes list of continuous Attributes and their thresholds
	 * @return information gain for a certain Attribute
	 */
	public static double getInformationGain(double totalEntropy, Attribute f, 
										ArrayList<Instance> Instances,  ArrayList<String> outputValues)
	{
		
			double entropy = 0.0;
			
			for(String val: f.getPossibleValues())
			{
				
				//System.out.println(val);
				
				
				for(int i=0; i<outputValues.size();i++)
				{
					double a = 0;
					double numOfInstances = 0;		
					/*
					* If the Instance has this attribute value, count the number 
					* of each classification
					*/
					for(Instance e: Instances)
					{
						if(e.getValues().get(f.getIndex()).equalsIgnoreCase(val))
						{
							numOfInstances++;
							if(e.getClassification().equalsIgnoreCase(outputValues.get(i)))
								a++;
							
						}
					}
					double aFraction = a/numOfInstances;
					if(aFraction==0){
						entropy += 0.0;	
					}
					else{ 
						entropy += -aFraction*(Math.log(aFraction)/Math.log(2));
					}
					//System.out.println("entropy of "+val+": "+entropy+" for class: "+outputValues.get(i));
				}	
			}
			//System.out.println("entropy: "+entropy);
				return totalEntropy - entropy;
		
		
	}
	
	
	/**
	 * Calculates the Split(a,b) value
	 * @param f Attribute to calculate value on
	 * @param Instanceslist of Instances
	 * @param contAttributes list of continuous Attributes and thresholds
	 * @return
	 */
	public static double getSplitInformation(Attribute f, ArrayList<Instance> Instances)
	{
		
		
			
			double split = 0.0;
			
			for(String val: f.getPossibleValues())
			{
				double numOfInstances = 0;
				for(Instance e: Instances)
				{	
					/*
					* If the Instance has this attribute value, count the number 
					* of each classification
					*/
					if(e.getValues().get(f.getIndex()).equalsIgnoreCase(val))
					{
						numOfInstances++;
					}
				}
				
				double fraction = numOfInstances/Instances.size();
				
				//Sum the entropies
				split += fraction*(Math.log(fraction)/Math.log(2));
			}
			
				return  split;
		
	}
	
	public static ArrayList<Instance> getInstancesOfSameClass(ArrayList<Instance> examples, ArrayList<String> output,int index){
		ArrayList<Instance> sameClass = new ArrayList<Instance>();
		for(Instance i : examples){
			if(i.getClassification().equalsIgnoreCase(output.get(index))){
				sameClass.add(i);
			}
		}
		
		return sameClass ;
	}

	@SuppressWarnings("unused")
	public static int[][] recall(DecisionTree tree, ArrayList<Instance> examples, ArrayList<String> output){
		int s = output.size();
		int index1 = 0;
		int index2=0;
		int[][] resolution = new int[s][s];
		for(int i=0; i<s;i++){
			for(int j=0; j<s ; j++){
				resolution[i][j]=0;
			}
		}
		for(Instance e: examples)
		{
			
			double result= tree.classify(tree, e);
			if(result==1.0){
				String c = tree.getClassifierClassCorrect();
				for(int i=0; i<s;i++){
					if(output.get(i).equalsIgnoreCase(c))
						index1 = i;
						
						break;
				}
				resolution[index1][index1]++;
			}
			else if(result==0.0){
				String c = tree.getClassifierClassCorrect();
				String w = tree.getClassifierClassWrong();
				for(int i=0; i<s;i++){
					if(output.get(i).equalsIgnoreCase(c))
						index1 = i;
					if(output.get(i).equalsIgnoreCase(w))
						index2= i;
				}
				resolution[index1][index2]++;
			}
		}
		return resolution;
	}
	
	public static int[] takeClassification( ArrayList<Instance> examples, ArrayList<String> output){
		int s = output.size();
		int[] classification = new int[s];
		for(int i=0; i<s;i++){
			classification[i]=0;
		}
		for(int i=0; i<s;i++){
			for(Instance e : examples){
				if(e.getClassification().equalsIgnoreCase(output.get(i))){
					classification[i]++;
				}
			}
		}
		return classification;
	}
	

}
