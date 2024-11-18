import java.net.*;
import java.io.*;

public class FileServer {
    public static void main(String[] args) throws IOException {
        // Create a server socket on port 13267
        ServerSocket servsock = new ServerSocket(13267);
        while (true) {
            System.out.println("Waiting for clients...");
            
            // Accept an incoming connection
            Socket sock = servsock.accept();
            System.out.println("Accepted connection: " + sock);
            
            // Specify the file to send
            File myFile = new File("source.pdf");
            byte[] mybytearray = new byte[(int) myFile.length()];
            
            // Read the file into the byte array
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            
            // Send the file over the socket
            OutputStream os = sock.getOutputStream();
            System.out.println("Sending file...");
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            
            // Close the connection
            sock.close();
            System.out.println("File sent successfully!");
        }
    }
}
