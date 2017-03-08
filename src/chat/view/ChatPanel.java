package chat.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import chat.controller.ChatController;
import chat.controller.FileController;

public class ChatPanel extends JPanel {
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JScrollPane chatScrollPane;
	private JButton chatButton;
	private JButton sendTweet;
	private JButton save;
	private JButton load;
	private JButton searchTwitter;

	public ChatPanel(ChatController baseController) {
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 40);
		chatField = new JTextField(25);
		chatButton = new JButton("Chat");
		sendTweet = new JButton("Send Tweet");
		save = new JButton("Save");
		load = new JButton("Load");
		searchTwitter = new JButton("Search Twitter");

		chatScrollPane = new JScrollPane(chatDisplay);
		baseLayout.putConstraint(SpringLayout.SOUTH, searchTwitter, 0, SpringLayout.NORTH, chatScrollPane);

		setupPanel();
		setupLayout();
		setupListeners();
	}

	public void showMessage(String text) {
		this.chatDisplay.setText(text);
	}

	private void setupPanel() {
		this.setLayout(baseLayout);
		this.setBackground(Color.LIGHT_GRAY);
		this.add(chatScrollPane);
		this.add(chatButton);
		this.add(chatField);
		this.add(sendTweet);
		this.add(save);
		this.add(load);
		this.add(searchTwitter);
	}

	private void setupLayout() {
		baseLayout.putConstraint(SpringLayout.WEST, chatScrollPane, 66, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatScrollPane, 29, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 232, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 77, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -165, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatScrollPane, -18, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 6, SpringLayout.EAST, chatField);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 1, SpringLayout.NORTH, chatField);
		baseLayout.putConstraint(SpringLayout.EAST, sendTweet, 0, SpringLayout.EAST, chatButton);
		baseLayout.putConstraint(SpringLayout.NORTH, save, 0, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.EAST, save, 0, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, sendTweet, 0, SpringLayout.NORTH, load);
		baseLayout.putConstraint(SpringLayout.WEST, searchTwitter, 20, SpringLayout.EAST, load);
	}

	private void setupListeners() {
		chatField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userWords = e.getActionCommand();
				String botResponse = baseController.useChatbotCheckers(userWords);

				chatDisplay.append("\nYou said: " + userWords + "\n" + baseController.getChatbot().getUserName()
						+ " said: " + botResponse);
				chatField.setText("");
			}
		});

		chatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);

				chatDisplay.append("\nYou said: " + userWords + "\n" + baseController.getChatbot().getUserName()
						+ " said: " + botResponse);
				chatField.setText("");
				chatDisplay.setCaretPosition(chatDisplay.getDocument().getLength());
			}
		});

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				String fileName = chatField.getText();
				FileController.saveFile(baseController, fileName.trim(), chatDisplay.getText());
			}
		});
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				String fileName = chatField.getText() + ".txt";
				String saved = FileController.readFile(baseController, fileName);
				chatDisplay.setText(saved);
			}
		});

		sendTweet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				baseController.useTwitter(chatField.getText());
			}
		});
		searchTwitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				String username = chatField.getText();
				chatDisplay.append(baseController.searchTwitter(username));
			}
		});
	}
}
