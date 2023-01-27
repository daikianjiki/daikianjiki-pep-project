package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
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
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if (account.username != null && account.password.length() >= 4 && !account.username.contains(account.username)) {
            //registration is successful
            context.json(addedAccount);
        } else {
            context.status(400);
        }
    }

    private void postLoginHandler(Context context) {
        context.json("sample text");
    }

    private void postMessagesHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesHandler(Context context) {
        context.json("sample text");
    }

    private void getMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void deleteMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void patchMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesByAccountIdHandler(Context context) {
        context.json("sample text");
    }


}