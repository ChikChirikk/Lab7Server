package Utilites;

import java.io.*;

public class Deserializator {
    Object object;
    public Object toDeserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
            byteArrayInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return "nope";
        }
    }
}