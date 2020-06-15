package Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Handler implements Runnable{
    private String login;
    private ServerReceiver receiver;
    private ServerSender sender;
    private App app;
    private HashMap portLoginPassword;
    private ArrayList commandAndArgument;

    public Handler(App app, ServerReceiver receiver, ServerSender serverSender) {
        this.sender = serverSender;
        this.receiver = receiver;
        this.app = app;
    }
    @Override
    public void run() {
        try {
            app.checkForSaveCommand();
            System.out.println("Handler " + Thread.currentThread().getName());
            HashMap portLoginPassword = (HashMap) receiver.receiveObject();
            String login = (String) portLoginPassword.get("login");
            User user = new User(login, app, receiver, sender);
            user.setPortLoginPassword(portLoginPassword);
            //user.setCommandAndArgument(receiver.receiveCommand());
            user.start();
            Thread.currentThread().sleep(20);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
//    public void run() throws IOException, ClassNotFoundException, SQLException {
//        try {
//            while (true) {
//                app.checkForSaveCommand();
//                HashMap portLoginPassword = (HashMap) receiver.receiveObject();
//                String login = (String) portLoginPassword.get("login");
//                User user = new User(login, this, receiver, sender);
//                user.setPortLoginPassword(portLoginPassword);
//                //user.setCommandAndArgument(receiver.receiveCommand());
//                user.start();
//                Thread.currentThread().sleep(20);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            this.begin();
//            this.run();
//
//        }
//    }