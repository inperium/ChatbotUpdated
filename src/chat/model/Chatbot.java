package chat.model;

import java.util.ArrayList;

/**
 * Base version of the 2016 Chatbot class. Only stub methods are provided.
 * Students will complete methods as part * of the project. * @author Isaac
 * Bowen * @version 1.0 10/14/16
 */
public class Chatbot
{
	
	/**
	 * The list of memes.
	 */
	
	private ArrayList<String> memesList;
	
	/**
	 * The list of political topics.
	 */
	
	private ArrayList<String> politicalTopicList;
	
	/**
	 * The name of the chatBot.
	 */
	
	private String userName;
	
	/**
	 * The topic being discussed.
	 */
	
	private String content;

	/**
	 * Creates an instance of the Chatbot with the supplied username. 
	 * @param userName The username for the chatbot. 
	 */
	
	public Chatbot(String userName)
	{
		this.politicalTopicList = new ArrayList<String>();
		this.content = "Science";
		this.memesList = new ArrayList<String>();
		this.userName = userName;
		this.buildMemesList();
		this.buildPoliticalTopicsList();
	}

	/**
	 * Creates a list of valid Memes that the Chatbot will recognize.
	 */
	
	private void buildMemesList()
	{
		memesList.add("Harambe");
		memesList.add("harambe");
		memesList.add("john cena");
		memesList.add("Arthur");
		memesList.add("cat");
		memesList.add("john");
		memesList.add("cute animals");
		memesList.add("babies");
		memesList.add("mr steal yo girl");
		memesList.add("steal your girl");
		memesList.add("doge");
		memesList.add("dat boy");
		memesList.add("clowns");
		memesList.add("fracking");
		memesList.add("Pokemon Go");
		memesList.add("Willy Wonka");
		memesList.add("Juan Cena");
		memesList.add("the wall");
		memesList.add("Dancing baby");
		memesList.add("Happy Tree Friends");
		memesList.add("Success Kid");
		memesList.add("willy wonka");
		memesList.add("grumpy cat");
		memesList.add("dat boi");
	}

	/**
	 * Creates a list of valid Political Topics that the Chatbot will recognize.
	 */
	
	private void buildPoliticalTopicsList()
	{
		politicalTopicList.add("Trump");
		politicalTopicList.add("Hillary");
		politicalTopicList.add("Jill Stein");
		politicalTopicList.add("Putin");
		politicalTopicList.add("Russian Hackers");
		politicalTopicList.add("Mexico");
		politicalTopicList.add("the wall");
		politicalTopicList.add("deleted emails");
		politicalTopicList.add("voting fraud");
		politicalTopicList.add("Libertarian");
		politicalTopicList.add("tea party");
		politicalTopicList.add("racism");
		politicalTopicList.add("taxes");
		politicalTopicList.add("Obama Care");
		politicalTopicList.add("Radical Islam");
		politicalTopicList.add("money");
		politicalTopicList.add("Gary Johnson");
		politicalTopicList.add("Bernie Sanders");
		politicalTopicList.add("DNC");
		politicalTopicList.add("RNC");
		politicalTopicList.add("Johnson");
		politicalTopicList.add("communism");
		politicalTopicList.add("news");
		politicalTopicList.add("CNN");
		politicalTopicList.add("Fox");
		politicalTopicList.add("judge");
		politicalTopicList.add("Democratic");
		politicalTopicList.add("Republican");
		politicalTopicList.add("Democrat");
		politicalTopicList.add("supreme court");
		politicalTopicList.add("campain");
		politicalTopicList.add("Hillary for prison");
		politicalTopicList.add("illegal immigrants");
		politicalTopicList.add("election");
		politicalTopicList.add("Kaine");
		politicalTopicList.add("liberal");
		politicalTopicList.add("conservative");
		politicalTopicList.add("Clinton");
		politicalTopicList.add("Pence");
		politicalTopicList.add("Stein");
		politicalTopicList.add("11/8/16");
		
	}

	/**
	 * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true. * @param currentInput * @return
	 * A true or false based on the length of the supplied String.
	 */

	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		if (currentInput != null && currentInput.length() > 0)
		{
			hasLength = true;
		}

		return hasLength;
	}

	/**
	 * Checks if the supplied String matches the content area for this Chatbot instance.
	 * @param currentInput The supplied String to be checked. * @return Whether it matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean check = false;
		if(currentInput.toLowerCase().contains(content.toLowerCase())){
			check = true;
		}
		return check;
	}
	
	/**
	 * Checks if the supplied String contains a reference to Harambe.
	 * @param currentInput The supplied String to be checked. 
	 * @return Whether or not it contains Harambe.
	 */
	
	public boolean harambeChecker(String currentInput)
	{	
	String containHarambe = "harambe";
	boolean harambe = false;
	
	if(currentInput.toLowerCase().contains(containHarambe.toLowerCase()))
	{
		harambe = true;
	}
	
	return harambe;
	}

	/**
	 * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it
	 * does not match. 
	 * @param currentInput The supplied String to be checked. * @return Whether the String is contained in the ArrayList.
	 */
	
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean politicalChecker = false;
		for(int politicalNames = 0; politicalNames < politicalTopicList.size(); politicalNames++)
		{
			if(currentInput != null && currentInput.equals(politicalTopicList.get(politicalNames)))
			{
				politicalChecker = true;
			}
		}

		return politicalChecker;
	}

	/**
	 *Checks to see that the supplied String value is in the current
	 * memesList variable.
	 * @param currentInput The supplied String to be checked. * @return Whether the supplied String is a recognized meme.
	 */
	
	public boolean memeChecker(String currentInput)
	{
		boolean memeChecker = false;
		for(int memeNames = 0; memeNames < memesList.size(); memeNames++)
		{
			if(currentInput != null && currentInput.equalsIgnoreCase(memesList.get(memeNames)))
			{
					memeChecker = true;
					ArrayList<String> invalidMemes = new ArrayList<String>();
					invalidMemes.add("dat boy");
					invalidMemes.add("ambe");
					invalidMemes.add("john");
					if(invalidMemes.contains(currentInput.toLowerCase())){
						memeChecker = false;
					}
			}
		}

		return memeChecker;

	}

	/**
	 * Returns the username of this Chatbot instance. 
	 * * @return The username of the Chatbot.
	 */
	
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * Sets the name of the Chatbot. 
	 * @param The username of the Chatbot.
	 */	
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * Returns the content of this Chatbot. 
	 * @return The content of the Chatbot.
	 */
	
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Sets the content of the Chatbot. 
	 * @param The content of the Chatbot.
	 */	
	
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 *Getter method for the memesList object. 
	 * @return The reference to the Meme list.
	 */
	
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}

	/**
	 * Getter method for the politicalTopicList object. 
	 * @return The reference to the political topic list.
	 */
	
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}

	/**
	 * Checks the input to see if it was spam. 
	 * @param The current user's input.
	 * @return Whether or not the response was spam.
	 */
	
	public boolean keyboardMashChecker(String currentInput)
	{
		boolean mash = false;
		
		if(currentInput.contains("sdf")){
			mash = true;
		}else if(currentInput.contains("dfg")){
			mash = true;
		}else if(currentInput.contains("asdf")){
			mash = true;
		}else if(currentInput.contains("jkl;")){
			mash = true;
		}else if(currentInput.contains("asdfjkl;")){
			mash = true;
		}else if(currentInput.contains("dfg")){
			mash = true;
		}else if(currentInput.contains("cvb")){
			mash = true;
		}else if(currentInput.contains(",./")){
			mash = true;
		}
		return mash;
	}
	
	/**
	 * Checks the input to see if it contains valid HTML. 
	 * @param The current user's input.
	 * @return Whether or not the response contains HTML.
	 */
	
	public boolean inputHTMLChecker(String test){
		int firstOpenTagIndex = -1;
		int firstCloseTagIndex = -1;
		int secondOpenTagIndex = -1;
		int secondCloseTagIndex = -1;
		
		if(test.length() < 3){ // <>
			return false;
		}
		
		firstOpenTagIndex = test.indexOf('<');
		firstCloseTagIndex = test.indexOf('>');
		
		if(firstOpenTagIndex < 0 || firstCloseTagIndex < 0){ // Coulnd't find < || >
			return false;
		}
		
		String firstTag = test.substring(firstOpenTagIndex+1, firstCloseTagIndex);
		
		if(firstTag.equalsIgnoreCase("p")){ // <P>
			return true;
		}else if(firstTag.replaceAll("\\s", "").equals("")){ // < >
			return false;
		}
		
		secondOpenTagIndex = test.indexOf('<', firstOpenTagIndex+1);
		secondCloseTagIndex = test.indexOf('>', firstCloseTagIndex+1);
		
		if(secondOpenTagIndex < 0 || secondCloseTagIndex < 0 || firstOpenTagIndex == secondOpenTagIndex || firstCloseTagIndex == secondCloseTagIndex){
			return false;
		}
		String secondTag = test.substring(secondOpenTagIndex+2, secondCloseTagIndex);
		
		if(firstTag.toLowerCase().startsWith(secondTag.toLowerCase())){
			if(firstTag.toLowerCase().contains("href")){
				if(!firstTag.contains("=")){
					return false;
				}
				return true;
			}else{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks the input to see if the quit command has been entered. 
	 * @param The current user's input.
	 * @return Whether or not the response contains HTML.
	 */
	
	public boolean quitChecker(String input)
	{
		boolean quit = false;
		if(input.equalsIgnoreCase("quit"))
		{
			quit = true;
		}
		return quit;
	}
	
	/**
	 * Checks the input to see if it contains twitter related characters. 
	 * @param The current user's input.
	 * @return Whether or not the response contains twitter related characters.
	 */
	
	public boolean twitterChecker(String input)
	{
		boolean checkTwitter = false;
		if(input.contains("@") || input.contains("#")){
			checkTwitter = true;
		}

		return checkTwitter;
	}

}