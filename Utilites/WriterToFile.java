package Utilites;

import java.io.*;

/**
 * writeToFile
 *
 * @author Diana
 */
public class WriterToFile {
    private static String filename;

    public void setFilename(String name) {
        this.filename = name;
    }

    public String getFilename() {
        return filename;
    }

    /**
     * write to file
     *
     * @param data string value, that you want to write
     */
    public static void writeToFile(String data) throws IOException {
        OutputStream outputStream = null;
        outputStream = new FileOutputStream(new File(WriterToFile.filename));
        byte[] dataToBytes = data.getBytes();
        outputStream.write(dataToBytes);
    }
}