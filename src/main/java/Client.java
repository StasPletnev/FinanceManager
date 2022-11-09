import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        File file = new File("JSONRequest.json");
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
                out.write(json + "\n");
                out.flush();
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
