import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

/**
 * This class queries the definitions of words in a created dictionary
 * @author Jiaxin Lu
 *
 */
public class Query {
	
	public static void main(String[] args){
		//create a new dictionary to hold data entries including word, definition, and definition type
		OrderedDictionary orderedDict = new OrderedDictionary();
		try{
			//create a new BufferedReader
		    BufferedReader buffer=new BufferedReader(new FileReader(args[0]));
			String words, definition;     //variable declaration
			words = buffer.readLine();
			//read words line by line
			while (words != null){
				words = words.toLowerCase();      //all words will be converted to lower case
				definition = buffer.readLine();   //read definitions
				
				//insert a data entry into dictionary as picture file
				if (definition.endsWith(".jpg")||definition.endsWith(".gif"))
					orderedDict.insert(words, definition, 3);
				//insert a data entry into dictionary as a audio file
				else if (definition.endsWith(".wav")||definition.endsWith(".mid"))
					orderedDict.insert(words, definition, 2);
				//insert a data entry into dictionary as text file 
				else
					orderedDict.insert(words, definition, 1);
				words = buffer.readLine();
			}
		}catch(Exception e) {   //Error handling code
	          System.out.println("Error: can't read keyboard input");
	          System.exit(0);
	        }
		
		//variable declarations
		StringReader keyboard = new StringReader();  //create a new StringReader object 
		StringTokenizer commands;
		String line, command, word;
		
		for(;;){      //Continue querying the user for input except user entering "end"
			 line = keyboard.read("Enter next command: ");
			 commands = new StringTokenizer(line);       //break the command into tokens
			 
			 if (commands.hasMoreElements())    //check if a command is given
                command = commands.nextToken();	  //initialize the command if command is given
			 else 
				command = "";                   //if no command is given, then empty string
			 
			 if (commands.hasMoreElements()){   //check if a word is given 
				 word = commands.nextToken();    //initialize the word if is given
				 word = word.toLowerCase();     //all words will be converted to lower case
			 }
			 else
				 word = null;         //otherwise, word is set null
			 
			 //check if the command is "define"
			 if (command.compareTo("define") == 0){
				 if (word == null)     //give a message if word is not given
					 System.out.println ("A word is needed to be argument.");
				 
				 else if (orderedDict.findType(word) == 1)   //display the definition on screen if it's text
					 System.out.println (orderedDict.findWord(word));
				 
				 else if (orderedDict.findType(word) == 2){  //play the audio file if definition type is audio type
					 try{
						 SoundPlayer sound = new SoundPlayer();
						 sound.play (orderedDict.findWord(word));
					 } catch (MultimediaException e){   //error handling code
						 System.out.println ("Could not play sound.");
					 }
				 }
				 
				 else if (orderedDict.findType(word) == 3){  //show the picture if definition type is pic file
					try{
						PictureViewer picture = new PictureViewer();
						picture.show (orderedDict.findWord(word));
					}catch (MultimediaException e){   //error handling code
						 System.out.println ("Could not show picture.");
					 }
				 }
			 }
			 
			 //check if the command is "list"
			 else if (command.compareTo("list") == 0){
				 if (word == null)     //give a message if word is not given
					 System.out.println ("A word is needed to be argument.");
				 
				 else {
					 try {
						 String prefix = word;
						 orderedDict.insert(prefix, "", 1);  //insert the prefix in dictionary
						 
						 word = orderedDict.successor(prefix);
						 //find words containing the given prefix, and then print them out
						 while (word.startsWith(prefix)){
							 System.out.print(word + ", ");
							 word = orderedDict.successor(word);
						 }
						 
						 System.out.println("");  //Move to a new line
						 orderedDict.remove(prefix);  //Remove the prefix from orderedDict	 
					 }catch (DictionaryException e){   //error handling code
							System.out.println(e.getMessage());
							}
				 }
			 }
			 
			 
			 //check if the command is "next"
			 else if (command.compareTo("next") == 0){
				 if (word == null)   //If a word is not given in the command, print a message
					 System.out.println ("A word is needed to be argument.");
				 
				 else if (orderedDict.findWord (word) == "")  //The word is not in dictionary
					 System.out.println ("The word is not in the dictionary."); 
				 
				 else{                                      //The word is in the dictionary
					 if (orderedDict.successor(word) == "")  //But no next word after the given word
						 System.out.println ("This is the last word in dictionary. There is no next word after that.");
					 else                                  //print the next word of the given word
						 System.out.println ("The next word is " + orderedDict.successor(word));
				 }
			 }
			 
			 //check if the command is "previous"
			 else if (command.compareTo("previous") == 0){
				 if (word == null)   //If a word is not given in the command, print a message
					 System.out.println ("A word is needed to be argument.");
				 
				 else if (orderedDict.findWord(word) == "")  //given word not in the dictionary
					 System.out.println ("The word is not in the dictionary.");
				 
				 else{    //given word in the dictionary
					 if (orderedDict.predecessor(word) == "")   //the word has no predecessor
						 System.out.println ("This is the first word in dictionary. There is no previous word.");
					 else       //print the predecessor of the given word
						 System.out.println ("The previous word is " + orderedDict.predecessor(word));
				 }
			 }
			 
			 //check if the command is "delete"
			 else if (command.compareTo("delete") == 0){
				 if (word == null)   //If a word is not given in the command, print a message
					 System.out.println ("A word is needed to be argument.");
				 
				 else{
					 try{
						 orderedDict.remove(word);
					 }catch (DictionaryException e){   //error handling code
						 System.out.println(e.getMessage()); 
					 }
				 }
			 }
			 
			 
			 //check if the command is "end". Stop the program if it is  
			 else if (command.compareTo ("end") == 0){
				 break;
			 }
			 
			 else{   //give a message if this is not a command
				 System.out.println ("This is not a command, please try again.");
			 }
					 
		}
		
		
	}
}
