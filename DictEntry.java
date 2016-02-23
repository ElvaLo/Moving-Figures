/**
 * This class represents a DictEntry that stores the word, definition, and definition type 
 * @author Jiaxin Lu
 *
 */
public class DictEntry {
	/* Attribute declarations */
	private String word;
	private String definition;
	private int type;
	
	/**
	 * Constructor initializes the DictEntry's word, definition, and type
	 * @param word name of the word
	 * @param definition definition of the word
	 * @param type definition type of the word
	 */
	public DictEntry (String word, String definition, int type){
		this.word = word;
		this.definition = definition;
		this.type = type;
	}
	
	/**
	 * word method returns the word in DictEntry
	 * @return word 
	 */
	public String word(){
		return word;
	}
    /**
     * definition method returns the definition in the DictEntry
     * @return definition
     */
	public String definition(){
		return definition;
	}
	
	/**
	 * type method returns the definition type in the DictEntry
	 * @return type
	 */
	public int type(){
		return type;
	}
}
