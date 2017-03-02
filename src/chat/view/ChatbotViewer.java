package chat.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ChatbotViewer
{

	private String windowMessage;
	private ImageIcon chatIcon;
	
	public ChatbotViewer()
	{
		windowMessage = "This message brought to you by Isaac.";
		chatIcon = new ImageIcon(getClass().getResource("images/chat.png"));
	}
	
	public String collectResponse(String message)
	{
		String response = "";
		response = JOptionPane.showInputDialog(null, message, windowMessage, JOptionPane.INFORMATION_MESSAGE, chatIcon, null, "Type here").toString();
		return response;
	}

	public int collectOption(String question)
	{
		int response = 0;
		response = JOptionPane.showConfirmDialog(null, question, windowMessage, JOptionPane.DEFAULT_OPTION, 0, chatIcon);
		return response;
	}

	public void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message, windowMessage, JOptionPane.INFORMATION_MESSAGE, chatIcon);
	}

}
