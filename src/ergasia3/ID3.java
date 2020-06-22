//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.util.ArrayList;

public class ID3 {
	private ArrayList<Attribute> Attributes;
	private ArrayList<Instance>trainingInstances;
	private ArrayList<Instance>testInstances;
	private ArrayList<Instance> tuningInstances = new ArrayList<Instance>();
	private ArrayList<Instance> tuningInstances2 = new ArrayList<Instance>();
	private ArrayList<Instance> tuningInstances3 = new ArrayList<Instance>();
	private ArrayList<Instance> tuningInstances4 = new ArrayList<Instance>();
	private int numbering = 0;
	private int outputIndex;
	private ArrayList<String> outputValues;
	
	public ID3()
	{
		
	}

	/**
	 * Method that runs reads the appropriate files and 
	 * runs the id3 learning algorithm with pruning
	 * @param namesFile file containing Attributes
	 * @param trainsetFile file containing the training set
	 * @param testsetFile file containing the test set
	 * @param splittingFunction 
	 */
	public void run_id3(String namesFile, String trainsetFile, String testsetFile)
	{
		DecisionTree bestTree;
		DecisionTree bestTree2;
		DecisionTree bestTree3;
		DecisionTree bestTree4;
		DecisionTree bestTree5 = null;
		double scoreOfBestTree = 0.0;
		double scoreOfBestTree2 = 0.0;
		double scoreOfBestTree3 = 0.0;
		double scoreOfBestTree4 = 0.0;
		double scoreOfBestTree5 = 0.0;
		double error = 0.0;
		double error2 = 0.0;
		double error3 = 0.0;
		double error4 = 0.0;
		double error5 = 0.0;
		double sum = 0.0;
		
		//Read files and store Instances
		readFiles(namesFile, trainsetFile, testsetFile);
		
		//Create tuning set as 20% of the training set and create a train' set
		int numOfTuningInstances = (int)Math.floor((trainingInstances.size() * 0.2));
		for(int k = 0; k < 3; k++)
		{
			tuningInstances.add(trainingInstances.get(k));
			
		}
		
		//Create tuning set as 40% of the training set and create a train' set
				numOfTuningInstances = (int)Math.floor((trainingInstances.size() * 0.4));
				for(int k = 0; k < numOfTuningInstances; k++)
				{
					tuningInstances2.add(trainingInstances.get(k));
					
				}
				
				//Create tuning set as 60% of the training set and create a train' set
				numOfTuningInstances = (int)Math.floor((trainingInstances.size() * 0.6));
				
				for(int k = 0; k < numOfTuningInstances; k++)
				{
					
					tuningInstances3.add(trainingInstances.get(k));
				
					
					
				}
				
			//Create tuning set as 80% of the training set and create a train' set
				numOfTuningInstances = (int)Math.floor((trainingInstances.size() * 0.8));
				for(int k = 0; k < numOfTuningInstances; k++)
				{
					tuningInstances4.add(trainingInstances.get(k));
					
				}
		
		
		//Create tree that fits the 20% of training' sets 
		DecisionTree tree = id3( tuningInstances, Attributes);
		
		//Calculate score the tree on the tuning set
		
		for(Instance e: tuningInstances)
		{
			sum += tree.classify(tree, e);
		}
		
		//Set initial best score and best tree
		System.out.println("Size of 20% of train sets: "+tuningInstances.size());
		scoreOfBestTree = (sum/tuningInstances.size())*100;
		error = 100.0-scoreOfBestTree;
		System.out.println("score of train of 20% of Instances: "+scoreOfBestTree);
		System.out.println("error of train of 20% of Instances: "+error);
		System.out.println("////////////////////////////////////////////////////////");
		bestTree = tree;
		
		//Create tree that fits the 40% training' set
				DecisionTree tree2 = id3( tuningInstances2, Attributes);
				
				//Calculate score the tree on the tuning set
				sum = 0.0;
				for(Instance e: tuningInstances2)
				{
					sum += tree2.classify(tree2, e);
				}
				
				//Set initial best score and best tree
				System.out.println("Size of 40% of train sets: "+tuningInstances2.size());
				scoreOfBestTree2 = (sum/tuningInstances2.size())*100;
				error2 = 100.0-scoreOfBestTree2;
				System.out.println("score of train of 40% of Instances: "+scoreOfBestTree2);
				System.out.println("error of train of 40% of Instances: "+error2);
				System.out.println("////////////////////////////////////////////////////////");
				bestTree2 = tree2;
				
			//Create tree that fits the 60% training' set
				DecisionTree tree3 = id3( tuningInstances3, Attributes);
				
				//Calculate score the tree on the tuning set
				sum = 0.0;
				for(Instance e: tuningInstances3)
				{
					sum += tree3.classify(tree3, e);
				}
				
				//Set initial best score and best tree
				System.out.println("Size of 60% of train sets: "+tuningInstances3.size());
				scoreOfBestTree3 = (sum/tuningInstances3.size())*100;
				error3 = 100.0-scoreOfBestTree3;
				System.out.println("score of train of 60% of Instances: "+scoreOfBestTree3);
				System.out.println("error of train of 60% of Instances: "+error3);
				System.out.println("////////////////////////////////////////////////////////");
				bestTree3 = tree3;
				
				
			//Create tree that fits the 80% training' set
				DecisionTree tree4 = id3( tuningInstances4, Attributes);
				
				//Calculate score the tree on the tuning set
				sum = 0.0;
				for(Instance e: tuningInstances4)
				{
					sum += tree4.classify(tree4, e);
				}
				
				//Set initial best score and best tree
				System.out.println("Size of 80% of train sets: "+tuningInstances4.size());
				scoreOfBestTree4 = (sum/tuningInstances4.size())*100;
				error4 = 100.0-scoreOfBestTree4;
				System.out.println("score of train of 80% of Instances: "+scoreOfBestTree4);
				System.out.println("error of train of 80% of Instances: "+error4);
				System.out.println("////////////////////////////////////////////////////////");
				bestTree4 = tree4;
				
				
				/*
				ArrayList<Instance> class1 = Utility.getInstancesOfSameClass(trainingInstances, outputValues, 0);
				ArrayList<Instance> class2 = Utility.getInstancesOfSameClass(trainingInstances, outputValues, 1);
				System.out.println("Class 1: "+class1.size());
				System.out.println("Class 2: "+class2.size());
				int k=class1.size()/class2.size();
				int df = class1.size()-class2.size()*k; 
				System.out.println(k);
				System.out.println(df);
				for(int i=0; i<k; i++){
					trainingInstances.addAll(class2);
				}
				int i=0;
				while(i<df){
					trainingInstances.add(class2.get(i));
					i++;
				}
				*/
				
				//Create tree that fits the 100% of training' sets 
				DecisionTree tree5 ;
				
				tree5 = id3( trainingInstances, Attributes);
				
				//Calculate score the tree on the tuning set
				 sum = 0.0;
				for(Instance e: trainingInstances)
				{
					sum += tree5.classify(tree5, e);
				}
				
				//Set initial best score and best tree
				System.out.println("Size of 100% of train sets: "+trainingInstances.size());
				scoreOfBestTree5 = (sum/trainingInstances.size())*100;
				error5 = 100.0-scoreOfBestTree5;
				System.out.println("score of train of 100% of Instances: "+scoreOfBestTree5);
				System.out.println("error of train of 100% of Instances: "+error5);
				System.out.println("****************************************************");
				bestTree5 = tree5;
				
				
		
				
				
		//bestTree5.print();
		
		//Calculate score the best tree on the test set
		double total = 0.0;
		for(Instance e: testInstances)
		{
			total += bestTree5.classify(bestTree5, e);
		}
		//bestTree5.dump_tree();
		System.out.println(bestTree5.getRootNode().getInstances().size());
		System.out.println(bestTree5.getNumberOfNodes());
		double accuracyOfTest = (total/testInstances.size())*100;
		double errorOfTest =100-accuracyOfTest;
		System.out.println("////////////////////////////////////////////////////////////");
		System.out.println("Accuracy of DT learner: " + accuracyOfTest+"%");
		System.out.println("Error of DT learner: " + errorOfTest+"%");
		System.out.println("////////////////////////////////////////////////////////////");
		//System.out.println(total);
		int[][] res=Utility.recall(bestTree5, testInstances, outputValues);
		int[] cla = Utility.takeClassification(testInstances, outputValues);
		for(int i=0; i<res.length; i++){
			//System.out.println(i+" : "+cla[i]);
			if(cla[i]!=0){
				double precision=res[i][i]/cla[i];
				System.out.println("Precision of "+outputValues.get(i)+" : "+precision);
			}
			double recall=0.0;
			double column =0 ;
			for(int j=0; j<res.length; j++){
				
				
				column += res[j][i]  ;
				
				
			}
			if((column!=0)){
				recall=res[i][i]/column;
			}
			else{
				recall=0.0;
			}
			System.out.println("Recall of "+outputValues.get(i)+" : "+recall);
			
		}
		
		
		
		
	}
	
	/**
	 * Method that reads the files and creates a list of Attributes and Instances
	 * @param namesFile
	 * @param trainingFile
	 */
	private void readFiles(String namesFile, String trainingFile, String testFile)
	{
		
		//Create MyFileReader object
		Reader reader = new Reader();
		
		//Create Attribute list
		Attributes = reader.createAttributes(namesFile);
		
		
		
		//Determine the index for the output Attribute value
		for(int j = 0; j < Attributes.size(); j++)
		{	
			if(Attributes.get(j).getAttributeName().equalsIgnoreCase("class"));
			{
				outputIndex = j;
				outputValues = Attributes.get(j).getPossibleValues();
			}
		}
	
		//create list of training Instances
		trainingInstances = reader.createInstanceList(trainingFile, outputIndex);
		
		for(int i=0; i<Attributes.size();i++){
			ArrayList<String> unsorted = new ArrayList<String>();
			if(Attributes.get(i).isContinuous()){
				for(Instance e: trainingInstances){
					unsorted.add(e.getValues().get(i));
					
				}
				Attributes.get(i).setContinuousValues(unsorted);
			}
		}
		
		testInstances = reader.createInstanceList(testFile, outputIndex);
	
	}
	
	
	public DecisionTree id3(ArrayList<Instance> Instances, ArrayList<Attribute> attributes)
	{
		numbering++;
		//System.out.println("numbering: "+numbering);
		ArrayList<Attribute> newAttributeList = new ArrayList<Attribute>();
			
		
			for(int i = 0; i < attributes.size(); i++)
			{
				Attribute tmp = attributes.get(i);
				
				newAttributeList.add(tmp);
			}
		
		
		
		//Create a new root node
		Node root = new Node( Instances, outputValues, numbering);
		
		DecisionTree tree = new DecisionTree(root);
		
		/*If the root node contains all the same classifications, return the
		 * single tree node with the corresponding classification 
		 */
		if(root.isLeaf())
			return tree;
		
		/*
		 * If there are no more attibutes to test, simply 
		 * return the majority classification
		 */
		else if(newAttributeList.isEmpty())
		{
			
			tree.getRootNode().setClassification(root.getMajorityClassification());
			return tree;
		}
	
		/*
		 * Otherwise begin the algorithm to search for a DecisionTree
		 */
		else
		{
			
			double totalEntropy = Utility.getTotalEntropy(Instances,outputValues);
			Attribute bestAttribute = Utility.getBestAttribute(totalEntropy, Instances, newAttributeList, outputValues);
			root.setAttribute(bestAttribute);
			
			ArrayList<Attribute> newAttributeList2 = new ArrayList<Attribute>();
			for(int i = 0; i < newAttributeList.size(); i++)
			{
				newAttributeList2.add(newAttributeList.get(i));
			}
			
			
			
			
				int k = bestAttribute.getIndex();
				//System.out.println("k: "+k);
				//System.out.println(bestAttribute.getPossibleValues());
				//System.out.println(bestAttribute.getPossibleValues().size());
				for(String val: bestAttribute.getPossibleValues())
				{
					//Create a new subtree for each of the possible values
					ArrayList<Instance> newInstanceList = new ArrayList<Instance>();
					for(Instance e: Instances)
					{
						
						if(e.getValues().get(k).equalsIgnoreCase(val))
						{
							newInstanceList.add(e);
						}
					
					}
					//System.out.println("newInstanceList size: "+newInstanceList.size());
					DecisionTree child = new DecisionTree(new Node(newInstanceList, outputValues, numbering));
					//System.out.println("Root of child id: "+child.getRootNode().getId());
					child.getRootNode().setAttributeValue(val);
					//System.out.println("Discrete split: " + val);
			
					if(!child.getRootNode().isLeaf())
					{
						newAttributeList.remove(bestAttribute);
						child.addTree(id3(newInstanceList, newAttributeList));
						
					}
					
					else
					{
						child.getRootNode().setClassification(child.getRootNode().getMajorityClassification());
						tree.addTree(child);
					}
				}
			}
			//tree.print();
			return tree;	
		}
		
	
		
	
}
