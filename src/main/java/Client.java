import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        File file = new File("JSONRequest.json");
        byte[] byteArray = new byte[(int) file.length()];
        try (Socket clientSocket = new Socket("localhost", 8989)) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                System.out.println("Какую сумму вы потратите?");
                Scanner scanner = new Scanner(System.in);
                int sum = Integer.parseInt(scanner.next());
                System.out.println("На что тратите деньги?");
                String object = scanner.next();
                Request request = new Request(object, "21.02.2001", sum);
                Gson gson = new Gson();
                String json = gson.toJson(request);
                out.write(json);
                out.flush();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
//        try (Socket clientSocket = new Socket("localhost", 8989)) {
//            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("JSONRequest.json"));
//                 BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream())) {
//                while ((in.read(byteArray)) != -1) {
//                    out.write(byteArray, 0, (int) file.length());
//                }
//            }
//
//        } catch (IOException e) {
//            System.err.println(e);
//        }
    }
}
