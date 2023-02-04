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
        if (message.posted_by >= 1 && message.message_text != "" && message.message_text.length() <= 255) {
            return messageDAO.insertMessage(message);
        } 
        return null;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessageById(int id) {
        Message message = messageDAO.getMessageById(id);
        messageDAO.deleteMessageById(id);
        if (message != null) {
            return message;
        }
        return null;
    }

    public Message updateMessageById(int id, Message message) {
        if(message.message_text != "" && message.message_text.length() <= 255) {
            return messageDAO.updateMessageById(id, message);  
        }
        return null;
    }

    public List<Message> getAllMessagesByAccountId(int id) {
        return messageDAO.getAllMessagesByAccountId(id);
    }
}
