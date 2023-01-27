package Service;

import java.util.List;
import Model.Message;
import DAO.MessageDAO;



public class MessageService {
    private MessageDAO messageDAO;
    
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public List<Message> getAllMessages() {
        return null;
    }
}
