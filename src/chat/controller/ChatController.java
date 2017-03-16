package chat.controller;

import chat.model.CTECTwitter;
import chat.model.Chatbot;
import chat.view.ChatFrame;
import chat.view.ChatbotViewer;

public class ChatController {
	private Chatbot stupidBot;
	private ChatbotViewer display;
	private ChatFrame chatFrame;
	public String questionList[] = { "What is the meaning of life?", "What do you like to do?", "Where do you live?" };
	private CTECTwitter tweetBot;

	/**
	 * Constructs Chatbot, display, and chatFrame.
	 * 
	 * @param The
	 *            current user's input.
	 * @return Whether or not the response contains twitter related characters.
	 */

	public ChatController() {
		stupidBot = new Chatbot("Steve");
		tweetBot = new CTECTwitter(this);
		display = new ChatbotViewer();
		chatFrame = new ChatFrame(this);
	}

	/**
	 * Presents the user with a welcome message.
	 */

	public void start() {
		this.chatFrame.getChatPanel().showMessage("Welcome to Chatbot!");
	}

	/**
	 * Gets the Main Chatbot.
	 * 
	 * @return Main ChatBot
	 */
	public Chatbot getChatbot() {
		return stupidBot;
	}

	/**
	 * Runs the Chatbot checkers to see what it contains and formulate a
	 * response.
	 * 
	 * @param The
	 *            current user's input.
	 * @return Whether or not the response contains something the Chatbot knows.
	 */

	public String useChatbotCheckers(String input) {
		String answer = "";

		if (!stupidBot.quitChecker(input)) {
			if (stupidBot.contentChecker(input)) {
				answer += "\nYou konw my special secret\n";
			} else {
				if (stupidBot.keyboardMashChecker(input)) {
					answer += "Please do not spam.";
				} else {
					if (stupidBot.politicalTopicChecker(input)) {
						answer += "Would you like to talk more about " + input + "?";
					} else {
						if (stupidBot.harambeChecker(input)) {
							answer += "Harambe was innocent.\n";
						} else {
							if (stupidBot.memeChecker(input)) {
								answer += "I can has memes?\n";
							} else {
								if (stupidBot.inputHTMLChecker(input)) {
									answer += "Hey, that's HTML.";
								} else {
									if (stupidBot.twitterChecker(input)) {
										answer += "I twitter too.";
									} else {
										if (stupidBot.lengthChecker(input)) {
											answer += "Sorry, I don't know about " + input + ".";
										} else {
											answer += "Sometimes silence is the answer.";
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			display.displayMessage("Thank you for chatting!");

			System.exit(0);
		}
		return answer;
	}

	public void handleErrors(Exception currentException) {
		display.displayMessage("An error has occured. Details:");
		display.displayMessage(currentException.getMessage());
	}

	/**
	 * Gets the chatFrame.
	 * 
	 * @return The chatFrame.
	 */

	public ChatFrame getBaseFrame() {
		return this.chatFrame;
	}

	public Object getPopup() {
		return chatFrame;
	}

	public void useTwitter(String text) {
		tweetBot.sendTweet(text);
	}

	public String searchTwitter(String name) {
		String results = "";
		results += tweetBot.getMostTweetedWord(name);
		return results;
	}

	public String mostPopularAtLocation() {
		String results = "";
		results += tweetBot.mostPopularAtLocation();
		return results;
	}
}
