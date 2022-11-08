import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        File file = new File("JSONRequest.json");
        byte[] byteArray = new byte[(int) file.length()];
        if (!file.exists()) {
            Request request = new Request("какич", "21.02.2001", 200);
            request.JSONRequest();
        }
        try (Socket clientSocket = new Socket("localhost", 8989)) {
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("JSONRequest.json"));
                 BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream())) {
                while ((in.read(byteArray)) != -1) {
                    out.write(byteArray, 0, (int) file.length());
                }
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
