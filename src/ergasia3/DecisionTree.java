//IAKOVOS EVDAIMON 3130059
package ergasia3;

import java.util.ArrayList;

public class DecisionTree {
	private Node root;
	private ArrayList<DecisionTree> children = new ArrayList<DecisionTree>();
	private String clasCorrect;
	private String clasWrong;
	
	public  DecisionTree()
	{
		
	}
	
	public DecisionTree(Node n)
	{
		root = n;
	}
	
	/**
	 * Method to add a subtree to this decision tree
	 * @param dt
	 */
	public void addTree(DecisionTree dt)
	{
		children.add(dt);
	}
	
	/**
	 * Returns the root node of this tree
	 * @return the root node
	 */
	public Node getRootNode()
	{
		return root;
	}
	
	/**
	 * Returns the number of non-leaf nodes within this tree
	 * @return
	 */
	public int getNumberOfNodes()
	{
		int total = 0;
		for(DecisionTree dt: children)
		{
			for(int i = 0; i < dt.getChildren().size(); i++)
			{
				if(!dt.getChildren().get(i).getRootNode().isLeaf())
				{
					total++;
				}
			}
		}
		return total;
	}
	
	/**
	 * Returns an arrayList of the children of this decision tree
	 * @return
	 */
	public ArrayList<DecisionTree> getChildren()
	{
		return children;
	}
	public void dump_tree()
	{
		
		if(root.isLeaf())
		{
			System.out.println("Leaf Node: " + root.getClassification());
		}
		else if(root.getSplitValue() == null)
		{
			System.out.println("Leaf Node: " + root.getMajorityClassification());
		}
		else
		{
			System.out.println("NODE: " + root.getAttribute().getAttributeName() + ": " + root.getSplitValue());
			Attribute f = root.getAttribute();
			int featIndex = f.getIndex();
			
				for(DecisionTree dt: children)
				{
					dt.dump_tree();
				}
					
			
		}
		
	}
	
	/**
	 * Determines if this tree correctly classifies a given Instance
	 * @param tree tree to test
	 * @param e Instance to classify
	 * @return 1 if classified correctly, 0 if incorrectly
	 */
	public double classify(DecisionTree tree, Instance e)
	{
		double result = 0;
		//If the node is a leaf node just compare the classifications
		if(tree.getRootNode().isLeaf())
		{
			if(tree.getRootNode().getClassification().equalsIgnoreCase(e.getClassification())){
				result =  1.0;
				setClassifierClassCorrect(e.getClassification());
			}
			else{
				result = 0.0;
				setClassifierClassCorrect(e.getClassification());
				setClassifierClassWrong(tree.getRootNode().getClassification());
			}
		}
		
		//If the node is a leaf node but doesn't have a split value declared 
		//use the majority classification and compare
		if(tree.getRootNode().getSplitValue() == null)
		{
			//e.printInstance();
			if(tree.getRootNode().getMajorityClassification().equalsIgnoreCase(e.getClassification())){
				result =  1.0;
				setClassifierClassCorrect(e.getClassification());
			}
			else{
				result = 0.0;
				setClassifierClassCorrect(e.getClassification());
				setClassifierClassWrong(tree.getRootNode().getClassification());
			}
		}
		
	
		else
		{
			
			
			
				int featIndex = tree.getRootNode().getAttribute().getIndex();
				String InstanceVal = e.getValues().get(tree.getRootNode().getAttribute().getIndex());
				//FInd which path tocontinue down by selecting the correct child node
				if(tree.getRootNode().getSplitValue().equalsIgnoreCase(InstanceVal))
				{
					for(DecisionTree x: tree.getChildren())
					{
						for(int i=0; i<x.getRootNode().getInstances().size(); i++){
						if(x.getRootNode().getInstances().get(i).getValues().get(featIndex).equalsIgnoreCase(InstanceVal))
						{
							result = classify(x, e);
						}
						}
					}
				}
				
				else
					result = 0.0;
					
			

		}
		
		return result;
	}
	
	

	private void setClassifierClassCorrect(String clas) {
		this.clasCorrect=clas;
		
	}
	
	public String getClassifierClassCorrect() {
		return this.clasCorrect;
		
	}
	
	private void setClassifierClassWrong(String clas) {
		this.clasWrong=clas;
		
	}
	
	public String getClassifierClassWrong() {
		return this.clasWrong;
		
	}

	public void print(){
		System.out.println("ID:"+root.getId());
		for(int i=0; i<children.size();i++){
			children.get(i).print();
		}
	}
}
