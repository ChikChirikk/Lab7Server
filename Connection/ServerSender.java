package Connection;

import Human.HumanBeing;
import Utilites.Deserializator;
import Utilites.Serializator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ServerSender {
    Serializator serializator = new Serializator();

    boolean isOpen = true;
    static DatagramSocket serverSocket = null;
    byte[] sendData = new byte[2056];
    InetAddress address = InetAddress.getLocalHost();
    int port;

    public ServerSender(int port) throws IOException {
        serverSocket = new DatagramSocket(port);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPort(String port) {
        this.port = Integer.parseInt(port);
    }

    public void sendCollecton(ArrayList<HumanBeing> collection) {
        if (this.isOpen) {
            try {
                sendData = serializator.toSerialize(collection);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                serverSocket.send(sendPacket);
            } catch (Exception e) {
                System.out.println("Клиент вышел из программы.");
            }
        }
    }

    public void send(String text) throws IOException {
        if (this.isOpen) {
            sendData = text.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            serverSocket.send(sendPacket);

        }
    }

//    public Object innerReceive() throws IOException, ClassNotFoundException {
//            System.out.println(1);
//            DatagramPacket receivePacket = new DatagramPacket(sendData, sendData.length);
//            System.out.println();
//            serverSocket.receive(receivePacket);
//            Deserializator deserializator = new Deserializator();
//            Object object = deserializator.toDeserialize(sendData);
//            System.out.println(object);
//        return object;


    public int getPort() {
        return port;
    }
}
