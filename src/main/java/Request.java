import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;


public class Request {
    protected String title;
    protected String date;
    protected int sum;


    public Request(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public void JSONRequest(){
        File file = new File("JSONRequest.json");
        Gson gson = new Gson();
        try (FileWriter fileGSON = new FileWriter(String.valueOf(file))) {
            fileGSON.write(gson.toJson(this));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
