/**
 * This class represents a node that make up a binary search tree
 * @author Jiaxin Lu
 *
 */
public class BSTNode {
	/* Attribute declarations */
	private BSTNode parent;
	private BSTNode lChild;
	private BSTNode rChild;
	private DictEntry entry;
	
	/**
	 * Constructor that creates a new node object with entry and current node
	 * @param entry containing word, definition, and type
	 * @param current
	 */
	public BSTNode(DictEntry entry, BSTNode current){
		this.entry = entry;
		this.parent = current;  
		lChild = null;
		rChild = null;
	}
	
	/**
	 * Constructor that creates a new node object with entry
	 * @param entry entry containing word, definition, and type
	 */
	public BSTNode(DictEntry entry){
		this.entry = entry;
		parent = null;  
		lChild = null;
		rChild = null;
	}
	
	/**
	 * getEntry method access the data entry of a node
	 * @return entry of a node
	 */
	public DictEntry getEntry(){
		return entry;
	}
	
	/**
	 * getParent method access the parent of a node
	 * @return parent of a node
	 */
	public BSTNode getParent(){  
		return parent;
	}
	
	/**
	 * getLChild method access the left child of a node
	 * @return left child of a node
	 */
	public BSTNode getLChild(){
		return lChild;
	}
	
	/**
	 * getRChild method access the right child of a node
	 * @return right child of a node
	 */
	public BSTNode getRChild(){
		return rChild;
	}
	
	/**
	 * setEntry method modifies the data entry of a node
	 * @param newEntry new data entry
	 */
	public void setEntry(DictEntry newEntry){
		entry = newEntry;
	}
	
	/**
	 * setParent method modifies the parent of a node
	 * @param newParent new parent
	 */
	public void setParent(BSTNode newParent){
		parent = newParent;
	}
	
	/**
	 * setLChild method modifies the left child of a node
	 * @param newLChild new left child
	 */
	public void setLChild(BSTNode newLChild){
		lChild = newLChild;
	}
	
	/**
	 * setRChild method modifies the right child of a node
	 * @param newRChild new right child
	 */
	public void setRChild(BSTNode newRChild){
		rChild = newRChild;
	}
	
	
	
}
	
	

