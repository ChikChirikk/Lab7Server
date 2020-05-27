

import Connection.App;
import Connection.ServerReceiver;
import Connection.ServerSender;

import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            //DATA LOAD COLLECTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            ServerSender sender = new ServerSender(11413);
            ServerReceiver receiver = new ServerReceiver(1257);
            App app = new App(sender, receiver);
            receiver.receive();
            app.begin();
            app.run();
        }
        catch(BindException | SQLException | ParseException e){
            e.printStackTrace();
            System.out.println("Прости сервер, но работает уже другой.");
        }

//        String received = "";
//        while ((received = receiver.receive()).equals("")){ }
//        System.out.println(received);
    }
}