import java.net.*;
import java.io.*;

public class FileClient {
    public static void main(String[] args) throws IOException {
        int filesize = 6022386; // Specify an estimated filesize (adjust as needed)
        int bytesRead;
        int current = 0;

        // Connect to the server at localhost on port 13267
        Socket sock = new Socket("127.0.0.1", 13267);
        System.out.println("Connecting to server...");

        // Create a byte array to receive the file
        byte[] mybytearray = new byte[filesize];
        InputStream is = sock.getInputStream();
        FileOutputStream fos = new FileOutputStream("source-copy.pdf");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        
        // Read the file from the server
        bytesRead = is.read(mybytearray, 0, mybytearray.length);
        current = bytesRead;

        // Continue reading until the entire file is received
        do {
            bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
            if (bytesRead >= 0) current += bytesRead;
        } while (bytesRead > -1);

        // Write the received file to the local filesystem
        bos.write(mybytearray, 0, current);
        bos.flush();
        System.out.println("File received successfully!");
        
        // Close all connections
        bos.close();
        sock.close();
    }
}
