package chat.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chat.controller.ChatController;
import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class CTECTwitter {

	private ChatController baseController;
	private Twitter twitterBot;
	private List<Status> allTheTweets;
	private List<String> tweetedWords;

	public CTECTwitter(ChatController baseController) {
		this.baseController = baseController;
		this.twitterBot = TwitterFactory.getSingleton();
		this.allTheTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
	}

	public void sendTweet(String textToTweet) {
		try {
			twitterBot.updateStatus(textToTweet + " @ChatbotCTEC");
		} catch (TwitterException tweetError) {
			baseController.handleErrors(tweetError);
		} catch (Exception e) {
			baseController.handleErrors(e);
		}
	}

	private String[] createIgnoredWordArray() {
		String[] boringWords;
		int wordCount = 0;

		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		while (boringWordScanner.hasNextLine()) {
			boringWordScanner.nextLine();
			wordCount++;
		}
		boringWordScanner.close();

		boringWords = new String[wordCount];

		boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));

		for (int index = 0; index < boringWords.length; index++) {
			boringWords[index] = boringWordScanner.next();
		}
		boringWordScanner.close();

		return boringWords;
	}

	private void removeBoringWords() {
		String[] boringWords = createIgnoredWordArray();
		for (int index = 0; index < tweetedWords.size(); index++) {
			for (int boringIndex = 0; boringIndex < boringWords.length; boringIndex++) {
				if (tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex])) {
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}

	private void removeBlankWords() {
		for (int index = 0; index < tweetedWords.size(); index++) {
			if (tweetedWords.get(index).trim().equals("")) {
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	


	private void gatherTweets(String user) {
		tweetedWords.clear();
		allTheTweets.clear();

		int pageCount = 1;
		Paging statusPage = new Paging(1, 200);

		while (pageCount <= 10) {
			try {
				allTheTweets.addAll(twitterBot.getUserTimeline(user, statusPage));
			} catch (TwitterException e) {
				baseController.handleErrors(e);
			}
			pageCount++;
		}
	}

	private void turnTweetToWords() {
		for (Status currentStatus : allTheTweets) {
			String text = currentStatus.getText();
			String[] tweetWords = text.split(" ");
			for (int index = 0; index < tweetWords.length; index++) {
				tweetedWords.add(removePunctuation(tweetWords[index]));
			}
		}
	}

	public String getMostTweetedWord(String user) {
		String results = "";
		this.gatherTweets(user);
		this.turnTweetToWords();
		
		this.removeBoringWords();
		this.removeBlankWords();
		
		results = "from " + user + calculateTopWord();
		return results;
	}

	private String calculateTopWord() {
		String results = "";
		String topWord = "";
		int mostPopularIndex = 0;
		int popularCount = 0;

		for (int index = 0; index < tweetedWords.size(); index++) {
			int currentPopularity = 0;
			for (int searched = index + 1; searched < tweetedWords.size(); searched++) {
				if (tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)) && !tweetedWords.get(index).equals(topWord)) {
					currentPopularity++;
				}
				if (currentPopularity > popularCount) {
					popularCount = currentPopularity;
					mostPopularIndex = index;
					topWord = tweetedWords.get(mostPopularIndex);
				}
			}
		}
		results += topWord + " and it occured " + popularCount + " times out of "
				+ tweetedWords.size() +", AKA " + (DecimalFormat.getPercentInstance().format(((double) popularCount)/tweetedWords.size())) + ".";
		
		return results;
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ",'?!:;\"()[]^[]<>-";
		
		String scrubbedString = "";
		for (int i = 0; i < currentString.length(); i++){
			if(punctuation.indexOf(currentString.charAt(i))== -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		
		return scrubbedString;
	}
	
	public String mostPopularAtLocation() 
	{
		String results = "";
		tweetedWords.clear();
		Query query = new Query("");
		query.setCount(100);
		query.setGeoCode(new GeoLocation(38.8977,77.0365), 1000, Query.MILES);
		query.setLang("en");
		query.setSince("2016-1-1");
		try
		{
			QueryResult result =  twitterBot.search(query);
			//results += "Count : " + result.getTweets().size() + "\n";
			allTheTweets = result.getTweets();
//			for(Status tweet : result.getTweets())
//			{
//				tweetedWords.add(tweet.getText());
//			}
			this.turnTweetToWords();
			this.removeBoringWords();
			this.removeBlankWords();
			results = "The top word at the given location (Washington DC) is: " + calculateTopWord();
		}
		catch (TwitterException error)
		{
			error.printStackTrace();
		}
		return results;
	}
}
