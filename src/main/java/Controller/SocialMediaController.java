package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messeges/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIdHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    //  - The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, 
    //      and an Account with that username does not already exist. If all these conditions are met, the response body should contain a JSON of the Account, 
    //      including its account_id. The response status should be 200 OK, which is the default. The new account should be persisted to the database.
    //  - If the registration is not successful, the response status should be 400. (Client error)

    private void postRegisterHandler(Context context) throws JsonProcessingException {
        //This is fully functional.
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if (addedAccount != null) {
            context.json(addedAccount);
        } else {
            context.status(400);
        }
    }
    //  - The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. 
    //        If successful, the response body should contain a JSON of the account in the response body, including its account_id. The response status should be 200 OK, 
    //        which is the default.
    //  - If the login is not successful, the response status should be 401. (Unauthorized)

    private void postLoginHandler(Context context) throws JsonProcessingException {
        //This is kind of working. Failing postRegisterThenPostLoginTest.
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account loggedIn = accountService.checkLogin(account);
        if (loggedIn != null) {
            context.json(loggedIn);
        } else {
            context.status(401);
        }
    }
    //  - The creation of the message will be successful if and only if the message_text is not blank, is under 255 characters, and posted_by refers to a real, existing user. 
    //      If successful, the response body should contain a JSON of the message, including its message_id. The response status should be 200, which is the default. 
    //      The new message should be persisted to the database.
    //  - If the creation of the message is not successful, the response status should be 400. (Client error)

    private void postMessagesHandler(Context context) throws JsonProcessingException {
        //This is fully functional.
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if (addedMessage != null) {
            context.json(addedMessage);
        } else {
            context.status(400);
        }
    }
    //  - The response body should contain a JSON representation of a list containing all messages retrieved from the database. 
    //      It is expected for the list to simply be empty if there are no messages. The response status should always be 200, which is the default.

    private void getAllMessagesHandler(Context context) {
        //This is fully functional.
        List<Message> message = messageService.getAllMessages();
        context.json(message);
    }
    //  - The response body should contain a JSON representation of the message identified by the message_id. It is expected for the response body to simply be empty if 
    //      there is no such message. The response status should always be 200, which is the default.

    private void getMessageByIdHandler(Context context) {
        //not sure about this one. I might need to tweek this one. 
        // ObjectMapper om = new ObjectMapper();
        // Message message = om.readValue(context.body(), Message.class);
        String messages = context.pathParam("message_id");
        Message messageById = messageService.getMessageById(Integer.parseInt(messages));
        context.json(messageById);
    }
    //  - The deletion of an existing message should remove an existing message from the database. If the message existed, the response body should contain the now-deleted message. 
    //      The response status should be 200, which is the default.
    //  - If the message did not exist, the response status should be 200, but the response body should be empty. 
    //      This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE endpoint should respond with the same type of response.

    private void deleteMessageByIdHandler(Context context) {
        String messages = context.pathParam("message_id");
        Message deletedById = messageService.deleteMessageById(Integer.parseInt(messages));
        if ( deletedById != null) {
            context.json(deletedById);
        } else {
            context.json("");
        }
    }
    //  - The update of a message should be successful if and only if the message id already exists and the new message_text is not blank and is not over 255 characters. 
    //      If the update is successful, the response body should contain the full updated message (including message_id, posted_by, message_text, and time_posted_epoch), 
    //      and the response status should be 200, which is the default. The message existing on the database should have the updated message_text.
    //  - If the update of the message is not successful for any reason, the response status should be 400. (Client error)

    private void patchMessageByIdHandler(Context context) {
        String messages = context.pathParam("messagge_id");
        Message updatedMessage = messageService.updateMessageById(null);
        if (updatedMessage != null) {
            context.json(updatedMessage);
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesByAccountIdHandler(Context context) {
        context.json("sample text");
    }


}