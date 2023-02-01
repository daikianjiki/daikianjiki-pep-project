package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "insert into message (posted_by, message_text, time_posted_epoch) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generated_message_id = (int) resultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "select * from message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Message message = new Message(
                    resultset.getInt("message_id"),
                    resultset.getInt("posted_by"),
                    resultset.getString("message_text"),
                    resultset.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
    
    public Message getMessageById(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select * from message where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Message message = new Message(
                    resultset.getInt("message_id"),
                    resultset.getInt("posted_by"),
                    resultset.getString("message_text"),
                    resultset.getLong("time_posted_epoch")
                );
                return message;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message deleteMessageById(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "delete from message where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Message updateMessageById(int id, Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "update message set message_text = ? where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // public List<Message> getAllMessagesByAccountId(int id) {
    //     Connection connection = ConnectionUtil.getConnection();
    //     List<Message> messages = new ArrayList<>();
    //     try {
    //         String sql = "select * from message where posted_by = ?";
    //         PreparedStatement preparedStatement = connection.prepareStatement(sql);

    //         preparedStatement.setInt(1, id);

    //         ResultSet resultset = preparedStatement.executeQuery();
    //         while (resultset.next()) {
    //             Message message = new Message(
    //                 resultset.getInt("message_id"),
    //                 resultset.getInt("posted_by"),
    //                 resultset.getString("message_text"),
    //                 resultset.getLong("time_posted_epoch")
    //             );
    //             messages.add(message);
    //         }
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //     }
    //     return messages;
    // }
}
