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
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIdHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    private void postRegisterHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper(); //objectmapper helps with converting java to json
        Account account = om.readValue(context.body(), Account.class); //instantiating from Account to convert json string into java object.
        Account addedAccount = accountService.addAccount(account);  //instantiating from account to use the addAccount method to persist new account.
        if (addedAccount != null) { //checking if it exists.
            context.json(addedAccount);  //sending the response back to api. json calls result and sets content type to json.
        } else {
            context.status(400); //response status when addedAccount is null.
        }
    }

    private void postLoginHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper(); // I need objectmapper to convert java into json, and vice a versa. 
        Account account = om.readValue(context.body(), Account.class); // instantiating from Account to convert json string into java object.
        Account loggedIn = accountService.checkLogin(account); // instantiating from Account to call the service for checkLogin method.
        if (loggedIn != null) { //checking if it exists.
            context.json(loggedIn); //sending the response back to api. json calls result and sets content type to json. 
        } else {
            context.status(401); //response status when loggedIn is null. 
        }
    }

    private void postMessagesHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if (addedMessage != null) {
            context.json(addedMessage);
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesHandler(Context context) {
        List<Message> message = messageService.getAllMessages();
        context.json(message);
    }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException {
        String id = context.pathParam("message_id");
        Message gotMessage = messageService.getMessageById(Integer.parseInt(id));
        if (gotMessage != null) {
            context.json(gotMessage);
        } else {
            context.status(404);
        }
    }

    private void deleteMessageByIdHandler(Context context) {
        String id = context.pathParam("message_id");
        Message deletedById = messageService.deleteMessageById(Integer.parseInt(id));
        if ( deletedById != null) {
            context.json(deletedById);
        } else {
        context.status(200);
        }
    }

    private void patchMessageByIdHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class); // instantiating variable to convert json string into java object
        int id = Integer.parseInt(context.pathParam("message_id")); //insantiating variable to match a part of URL as a parameter and turn it into int.
        Message updatedMessage = messageService.updateMessageById(id, message); //using the service to get the updated message
        if (updatedMessage != null) {
            context.json(updatedMessage); //returning to api after converting it to json string from java object
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesByAccountIdHandler(Context context) {
        String message = context.pathParam("account_id");
        List<Message> messages = messageService.getAllMessagesByAccountId(Integer.parseInt(message));
        context.json(messages);
    }


}