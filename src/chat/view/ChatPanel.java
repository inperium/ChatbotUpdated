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

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private  JTextArea chatDisplay;
	private JTextField chatField;
	private JScrollPane chatScrollPane;
	private JButton chatButton;
	private JButton secondButton;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout= new SpringLayout();
		chatDisplay = new JTextArea(5,40);
		chatField = new JTextField(25);
		chatButton = new JButton("Chat");
		secondButton = new JButton("Quit Chat");
		chatScrollPane = new JScrollPane(chatDisplay);
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	public void showMessage(String text){
		this.chatDisplay.setText(text);
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.LIGHT_GRAY);
		this.add(chatScrollPane);
		this.add(chatButton);
		this.add(chatField);
		this.add(secondButton);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatScrollPane, 66, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatScrollPane, 29, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 232, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 77, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -165, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatScrollPane, -18, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 6, SpringLayout.EAST, chatField);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 1, SpringLayout.NORTH, chatField);
	}
	
	private void setupListeners()
	{
		chatField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String userWords = e.getActionCommand();
				String botResponse = baseController.useChatbotCheckers(userWords);
				
				chatDisplay.append("\nYou said: " + userWords + "\n" + baseController.getChatbot().getUserName() + " said: " + botResponse);
				chatField.setText("");
			}
		});
		
		chatButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);
				
				chatDisplay.append("\nYou said: " + userWords + "\n" + baseController.getChatbot().getUserName() + " said: " + botResponse);
				chatField.setText("");
				chatDisplay.setCaretPosition(chatDisplay.getDocument().getLength());
			}
		});
		
		secondButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
}
