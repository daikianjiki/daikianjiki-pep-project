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

    public Message getMessageById(int id) {

        return messageDAO.getMessageById(id);
    }

    public Message deleteMessageById(int id) {
        if (messageDAO.getMessageById(id) != null) {
            Message message = messageDAO.getMessageById(id);
            messageDAO.deleteMessageById(id);
            return message;
        }
        return null;
    }

    public Message updateMessageById(int id, Message message) {
        if(messageDAO.getMessageById(id) != null && message.message_text.length() != 0 && message.message_text.length() < 255) {
            messageDAO.updateMessageById(id, message);
            return messageDAO.getMessageById(id);
        }
        return null;
    }

    // public List<Message> getAllMessagesByAccountId(int id) {
    //     return messageDAO.getAllMessagesByAccountId(id);
    // }
}
