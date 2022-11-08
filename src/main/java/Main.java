import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Main {
    public static Base base;
    public static void main(String[] args) throws FileNotFoundException {
        File baseJSONFile = new File("Base.json");
        newOrNo(baseJSONFile);

        try (ServerSocket server = new ServerSocket(8989)) {
            while (true) {
                try (Socket clientSocket = server.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                    String text = in.readLine();
                    loadJSONRequest(text);
                    checkWord(loadJSONRequest(text));
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static JsonObject loadJSONRequest(String text) throws IOException {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(text);
        FileWriter file = new FileWriter("output.json");
        file.write(jo.toString());
        file.close();
        return jo;
    }
    public static void checkWord(JsonObject request) {
        String text = String.valueOf(request.get("title"));
        if (text.equals("\"булка\"") || text.equals("\"колбаса\"") || text.equals("\"сухарики\"") || text.equals("\"курица\"")) {
            System.out.println("Вы потратили на еду: " + Integer.parseInt(String.valueOf(request.get("sum"))));
            base.spendMoneyFood(Integer.parseInt(String.valueOf(request.get("sum"))));
            System.out.println("Осталось финансов на еду: " + base.maxFood.getSum());
        } else if (text.equals("\"тапки\"") || text.equals("\"шапка\"")) {
            System.out.println("Вы потратили на одежду: " + Integer.parseInt(String.valueOf(request.get("sum"))));
            base.spendMoneyClothes(Integer.parseInt(String.valueOf(request.get("sum"))));
            System.out.println("Осталось финансов на одежду: " + base.maxClothes.getSum());
        } else if (text.equals("\"акции\"")) {
            System.out.println("Вы потратили на финансы: " + Integer.parseInt(String.valueOf(request.get("sum"))));
            base.spendMoneyFinance(Integer.parseInt(String.valueOf(request.get("sum"))));
            System.out.println("Осталось на финансы: " + base.maxFinance.getSum());
        } else {
            System.out.println("Вы потратили на другое: " + Integer.parseInt(String.valueOf(request.get("sum"))));
            base.spendMoneyOther(Integer.parseInt(String.valueOf(request.get("sum"))));
            System.out.println("Осталось финансов на другое: " + base.other.getSum());
        }
        Gson gson = new Gson();
        try (FileWriter file = new FileWriter("Base.json")) {
            file.write(gson.toJson(base));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void newOrNo(File baseJSONFile) throws FileNotFoundException {
        if (!baseJSONFile.exists()) {
            MaxFood maxFood = new MaxFood("еда", 3_500_000);
            MaxClothes maxClothes = new MaxClothes("одежда", 1_000_000);
            MaxFinance maxFinance = new MaxFinance("финансы", 1_000_000);
            Other other = new Other("другое", 1_500_000);
            base = new Base(maxFood, maxClothes, maxFinance, other);
            Gson gson = new Gson();
            try (FileWriter file = new FileWriter("Base.json")) {
                file.write(gson.toJson(base));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("Base.json"));
            base = gson.fromJson(reader, Base.class);
        }
    }
}

//TODO Сервер почему то не присылает ничего в ответ
//TODO Осталось сделать ответ от сервера в формате JSON, есть варик сделать его через класс (как с запросом). Есть варик сделать его через методы в Base.
//TODO Нужно сделать так, чтобы клиент посылал много раз, а не каждый раз запускать
//TODO Нужно подключить файл с данными