package chat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chat.controller.ChatController;
import twitter4j.Paging;
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
		for (Status currentTweet : allTheTweets) {
			String text = currentTweet.getText();
			String[] tweetWords = text.split(" ");
			for (String word : tweetWords) {
				tweetedWords.add(word);
			}
		}
	}

	public String getMostTweetedWord(String user) {
		this.gatherTweets(user);
		this.removeBoringWords();
		this.removeBlankWords();

		String info = "What we were supposed to do" + "";

		return info;
	}

	private String calculateTopWord() {
		String results = "";
		String topWord = "";
		int mostPopularIndex = 0;
		int popularCount = 0;

		for (int index = 0; index < tweetedWords.size(); index++) {
			int currentPopularity = 0;
			for (int searched = index + 1; searched < tweetedWords.size(); searched++) {
				if (tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched))) {
					currentPopularity++;
				}
				if (currentPopularity > popularCount) {
					popularCount = currentPopularity;
					mostPopularIndex = index;
					topWord = tweetedWords.get(mostPopularIndex);
				}
				currentPopularity = 0;
			}
		}

		results += " the most popular word was " + topWord + ", and it occured " + popularCount + "times.";
		results += "\nThat means it has a percentage of " + ((double) popularCount / tweetedWords.size())*100 + "%";

		return results;
	}
}