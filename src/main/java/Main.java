import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Main {
    public static Base base;
    public static String typeRequest;

    public static void main(String[] args) throws IOException {
        File baseJSONFile = new File("Base.json");
        File categories = new File("categories.tsv");
        newOrNo(baseJSONFile);
        if (categories.exists()) {
            tsvParser();
        }
        try (ServerSocket server = new ServerSocket(8989)) {
            while (true) {
                try (Socket clientSocket = server.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                    String text = in.readLine();
                    loadJsonRequest(text);
                    CheckWord.checkWord(loadJsonRequest(text));
                    out.write(response() + "\n");
                    System.out.println("Ответ отправил");
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static JsonObject loadJsonRequest(String text) throws IOException {
        JsonParser jsonParser = new JsonParser();
        return (JsonObject) jsonParser.parse(text);
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

    public static void tsvParser() throws FileNotFoundException {
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);
        List<String[]> allRows = parser.parseAll(new FileReader("categories.tsv"));
//        String[] first = allRows.get(7);
//        System.out.println(Arrays.toString(first));
    }

    public static String response() {
        base.typeResponse(typeRequest);
        Gson gson = new Gson();
        String json = gson.toJson(base.typeResponse(typeRequest));
        return json;
    }
}