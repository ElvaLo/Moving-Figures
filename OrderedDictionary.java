/**
 * This class is a subclass of exception 
 * @author Jiaxin Lu
 *
 */
public class OrderedDictionary implements OrderedDictionaryADT{        
	/* Attribute declarations */
	private BSTNode root;
	private BSTNode current;
	
	/**
	 * Constructor creates a new OrderedDictionary implemented by binary search tree
	 */
	public OrderedDictionary(){
		root = null;
		current = root;
	}
	
	/**
	 * findWord method returns the definition of a given word
	 * or it returns an empty string if the word is not in dictionary
	 */
	public String findWord (String word){
		if (current == null)   //word not in the dictionary
			return "";       
		
		//check if the current node stores the same word as the given word
		else if (current.getEntry().word().compareTo(word) == 0){   
			return current.getEntry().definition();  //return the definition of given word
		}	
		
		//check if the word of current node is stored in the right subtree
		else if (current.getEntry().word().compareTo(word) < 0){
			current = current.getRChild();    // move to the right child
			String definition = findWord (word);   //recursive call on the right child
			current = root;    //reset the current node
			return definition;
		}
		
		//check if the word of current node is stored in the left subtree
		else{
			current = current.getLChild();     //move to the left child   
			String definition = findWord(word);     //recursive call on the left child
			current = root;       //reset the current node
			return definition;
		}
	}
	
	/**
	 * findType method returns -1 if word is not in dictionary
	 * otherwise, it returns the definition type of the word
	 */
	public int findType(String word){
		if (current == null)   //the word is not in the dictionary
			return -1;
		
		//check if the current node stores the same word as the given word
		else if (current.getEntry().word().compareTo(word) == 0){
			return current.getEntry().type();       //return definition type of the given word
		}
		
		//check if the word of current node is stored in the right subtree
		else if (current.getEntry().word().compareTo(word) < 0){
			current = current.getRChild();    //move to the right child
			int type = findType(word);      //recursive call on the right child
			current = root;                 //reset the current node
			return type;
		}
		
		//check if the word of current node is stored in the left subtree
		else{
			current = current.getLChild();       //move to the left child
			int type = findType(word);             //recursive call on the left child
			current = root;                     //reset the current node
			return type;
		}
	}
	
	/**
	 * insert method adds the word, its definition, and its type into the dictionary
	 */
	public void insert (String word, String definition, int type) throws DictionaryException{
		//check if the binary search tree(dictionary) is empty
		if (root == null){
			root = new BSTNode (new DictEntry(word, definition, type)); //add the data entry as root of the tree
			current = root;   // set the current node to be root
		}
		
		//check if the current node stores the same word as the given word
		else if (current.getEntry().word().compareTo(word) == 0){
			current = root;
			//throw DictionaryException if the word is already in dictionary 
			throw new DictionaryException ("The word is already in the dictionary");
		}
		
		//check if the word should go to the right subtree
		else if (current.getEntry().word().compareTo(word) < 0){
			// the current node has right child
			if (current.getRChild() != null){
				current = current.getRChild();       //access the right child
				insert (word, definition, type);    //test the right child in the same way
				current = root;                   //reset the current node
			}
			
			//the right child is a leaf
			else{
				current.setRChild(new BSTNode(new DictEntry(word, definition, type), current));   //setLChild(DictEntry entry, BSTNode current)
			} 
	
		}
		
		//check if the word should go to the left subtree
		else {
			//the current node has left child 
			if (current.getLChild() != null){
				current = current.getLChild();     //access the left child
				insert (word, definition, type);    //test the left child in the same way
				current = root;                    //reset the current node
			}
			
			//the right child is a leaf
			else{
				current.setLChild(new BSTNode(new DictEntry(word, definition, type), current));   //setLChild(DictEntry entry, BSTNode current)
			} 
		}
		
	}
	
	/**
	 * remove method deletes the entry with the given word 
	 */
	public void remove (String word) throws DictionaryException {
		BSTNode find = find(word);  //find the node with the given word
		//check if the node with given word is in dictionary
		if (find == null){
			//throw an error message if the node with given word not in dictionary
			throw new DictionaryException ("The word is not in dictionary.");
		}
		
		//the node with given word is in dictionary
		else{
			BSTNode toBeReplaced;
			BSTNode  toBeReplacedChild;
		
			//check if either child of the node to be removed is a leaf
		    if (find.getRChild()==null||find.getLChild()==null)
		    	toBeReplaced = find;
		    else    //node to be removed have two children
		    	toBeReplaced = successor (find);   //successor replace with the one to be removed
		    
		    //store either child of the node to be replaced
		    if (toBeReplaced.getRChild()!=null)
		    	toBeReplacedChild = toBeReplaced.getRChild();
		    else
		    	toBeReplacedChild = toBeReplaced.getLChild();
		    
		    //check if the children of toBeReplaced are null
		    if (toBeReplacedChild != null)
		    	toBeReplacedChild.setParent(toBeReplaced.getParent());
		    
		    //set the root to be toBeReplacedChild
		    if (toBeReplaced.getParent()==null)
		    	root = toBeReplacedChild;
		    else{
		    	if (toBeReplaced == toBeReplaced.getParent().getLChild())
		    		toBeReplaced.getParent().setLChild(toBeReplacedChild);
		    	else
		    		toBeReplaced.getParent().setRChild(toBeReplacedChild);
		    }
		    //put the data into the node
		    if(toBeReplaced != find)
		    	find.setEntry(toBeReplaced.getEntry());
		}
		
		
	}
	
	
	/**
	 * successor method returns the successor word of the given word
	 * otherwise, returns an empty string
	 */
	public String successor(String word){
		BSTNode successor = successor (find(word)); //store the successor node
		//check if successor is null
		if (successor==null)
			return " ";       //return empty string if null
		else return successor.getEntry().word();   //otherwise, return word of successor
	}
	
	/**
	 * successor method returns the successor of given node
	 * or it returns null if given node is null
	 * @param node given node
	 * @return successor node
	 */
	private BSTNode successor (BSTNode node){
		//check if given node is null
		if (node==null)
			return null;
		
		//check if given node has right child
		else if (node.getRChild()!=null){
			//the successor should be in the right subtree
			node = node.getRChild();
			
			//the successor is the last left child in subtree
			while(node.getLChild()!=null)
				node = node.getLChild();
			return node;
		}
		
		//traversing left up the tree we get the node with smaller value
	    BSTNode x = node;
	    BSTNode y = node.getParent();
		while(x==y.getRChild()&&y!=null){
			x=y;
			y=y.getParent();
		}
		return y;
	}
	
	/**
	 * predecessor method returns the predecessor word of the given word
	 * otherwise, returns an empty string
	 */
	public String predecessor(String word){
		BSTNode predecessor = predecessor (find(word));  //store the predecessor node
		//check if predecessor is null
		if (predecessor==null)
			return " ";    //return an empty string if is null
		else return predecessor.getEntry().word();   //otherwise, return the predecessor word
	}
	
	/**
	 * predecessor method returns the predecessor of given node
	 * or it returns null if given node is null
	 * @param node given node
	 * @return predecessor node
	 */
	private BSTNode predecessor (BSTNode node){
		//check if the node is null
		if (node==null)
			return null;
		
		//check if the given node has left child
		else if (node.getLChild()!=null){
			//the predecessor should be in the left subtree
			node = node.getLChild();
			
			//the predecessor is the last right child in subtree
			while(node.getRChild()!=null)
				node = node.getRChild();
			return node;
		}
		
		//traversing right up the tree we get the node with smaller value
		BSTNode x = node;
	    BSTNode y = node.getParent();
	    while(x==y.getLChild()&&y!=null){
	    	x=y;
	    	y=y.getParent();
	    }
	    return y;
	}
	
	/**
	 * find method returns the node with given word 
	 * @param word given word
	 * @return node with given word
	 */
	private BSTNode find (String word){
		//begins with the root
		BSTNode check = root;
		
		//check if current node is not null and not the same as given one
		while (check != null && check.getEntry().word().compareTo(word) != 0){
			//move to right child if searched value is less than given value
			if (check.getEntry().word().compareTo(word) <0)
				check = check.getRChild();
			//otherwise, move to the left child
			else
				check = check.getLChild();
		}
		return check;
	}
	
}























