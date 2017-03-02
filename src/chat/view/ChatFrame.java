package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatController;

public class ChatFrame extends JFrame
{
	private ChatController baseController;
	private ChatPanel chatPanel;
	
	public ChatFrame(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		this.chatPanel = new ChatPanel(baseController);
		
		this.setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(chatPanel);
		this.setTitle("Chat Bot");
		this.setSize(600, 400);
		this.setVisible(true);
	}
	
	public ChatPanel getChatPanel(){
		return this.chatPanel;
	}
}
