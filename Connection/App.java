package Connection;

import Commands.Save;
import Controller.HumanCollection;
import DataBase.UsersDataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class App {
    private static ServerReceiver receiver;
    private static ServerSender sender;
    private HumanCollection collection = new HumanCollection();
    private DataBaseController dataBase;
    private static App app;

    public App(ServerSender sender, ServerReceiver receiver) throws SQLException, ParseException {
        System.out.println("Работа сервера запущена");
        this.receiver = receiver;
        this.sender = sender;
        dataBase = new DataBaseController();
        collection.setArray(DataBaseController.getHumansDataBase().getCollection());
        app = this;
    }

    public static ServerReceiver getReceiver() {
        return receiver;
    }

    public static ServerSender getSender() {
        return sender;
    }

    public void begin() {
        try {
            UsersDataBase users = dataBase.getUserDataBase();
            HashMap loginPassword = (HashMap) App.receiver.receiveObject();
            sender.setPort(Integer.parseInt((String) loginPassword.get("port")));
            String login = String.valueOf(loginPassword.get("login"));
            String password = Coder.toCode(String.valueOf(loginPassword.get("password")));
            if (loginPassword.get("reg").equals("sign"))
                if (!(users.isValue("login", login))) {
                    users.addUser(login, password);
                    sender.send("valid");
                } else sender.send("notValid");
            else if (loginPassword.get("reg").equals("log")) {
                if (users.isValue("login", login)) {
                    if (users.isValue("password", password)) {
                        sender.send("valid");
                    } else sender.send("notValid");
                } else sender.send("notValid");
            }
            System.out.println("Клиент [" + loginPassword.get("login") + "] подключен к серверу.\n");
            System.out.println("Для сохранения текущего состояния коллекции используйте команду\"save\".");
            System.out.println("Клиенту [" + loginPassword.get("login") + "] отправлено сообщение:\nПрограмма подключена к серверу.\n");
            sender.send("Программа подключена к серверу.\n");
        } catch (Exception e) {
        }
    }

   public void run() throws IOException, ClassNotFoundException, SQLException {
       try {
           while (true) {
               app.checkForSaveCommand();
               HashMap portLoginPassword = (HashMap) receiver.receiveObject();
               String login = (String) portLoginPassword.get("login");
               User user = new User(login, this, receiver, sender);
               user.setPortLoginPassword(portLoginPassword);
               //user.setCommandAndArgument(receiver.receiveCommand());
               user.start();
               Thread.currentThread().sleep(20);

           }
       } catch (Exception e) {
           e.printStackTrace();
           this.begin();
           this.run();

       }
   }


    public void checkForSaveCommand() throws IOException {
        Thread backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) {
                            Save save = new Save();
                            save.execute(null);
                        }
                    }
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}




