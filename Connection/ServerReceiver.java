package Connection;

import Utilites.Deserializator;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class ServerReceiver {
    Deserializator deserializator = new Deserializator();
    ByteBuffer buffer = ByteBuffer.allocate(1000000);
    DatagramChannel channel;
    DatagramSocket socket;
    InetSocketAddress address;
    static int clientPort;
    private static List<HashMap> receivedHumans = Collections.synchronizedList(new ArrayList<HashMap>());

    public ServerReceiver(int serverPort) throws IOException {
        address = new InetSocketAddress("localhost", serverPort);
        channel = DatagramChannel.open();
        socket = channel.socket();
        channel.configureBlocking(false);
        channel.bind(address);
    }

    public int getPort() {
        return clientPort;
    }

    public String receive() {
        try {
            String s = "";
            while (true) {
                InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
                if (remoteAdress != null) {
                    buffer.flip();
                    int limit = buffer.limit();
                    byte bytes[] = new byte[limit];
                    buffer.get(bytes, 0, limit);
                    s = new String(bytes);
                    buffer.clear();
                    return s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {
        while (true) {
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                Object object = deserializator.toDeserialize(bytes);
                buffer.clear();
                HashMap packedHuman = (HashMap) object;
                if (packedHuman.get("human") != null) {
                    receivedHumans.add(packedHuman);
                    return this.receiveObject();
                }
                return object;
            }
        }
    }

    public ArrayList receiveCommand() throws IOException, ClassNotFoundException {
        int tumb = 0;
        while (true) {
            if (tumb == 5000000) System.out.println("Ожидание команды от клиента...");
            buffer.clear();
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                Object commandArgumentObject = deserializator.toDeserialize(bytes);
                buffer.clear();
                try {
                    HashMap packedHuman = (HashMap) commandArgumentObject;
                    if (packedHuman.get("human") != null) {
                        receivedHumans.add(packedHuman);
                        return this.receiveCommand();
                    }
                } catch (ClassCastException e) {
                }
                return (ArrayList) commandArgumentObject;
            } else tumb++;
        }
    }

    public static List<HashMap> getReceivedHumans() {
        return receivedHumans;
    }

    public static void removeReceivedHuman(int ind) {
        receivedHumans.remove(ind);
    }

}