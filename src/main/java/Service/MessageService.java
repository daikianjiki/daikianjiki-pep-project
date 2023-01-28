package Service;

import java.util.List;
import Model.Message;
import DAO.MessageDAO;



public class MessageService {
    private MessageDAO messageDAO;
    
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public Message addMessage(Message message) {
        if (message.message_text != "" && message.message_text.length() < 255) {
            return messageDAO.insertMessage(message);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int id) {
        return messageDAO.deleteMessageById(id);
    }

    public Message updateMessageById(int id, String message) {
        return messageDAO.updateMessageById(id, message);
    }

    public List<Message> getAllMessagesByAccountId(int message_id) {
        return messageDAO.getAllMessagesByAccountId(message_id);
    }
}
