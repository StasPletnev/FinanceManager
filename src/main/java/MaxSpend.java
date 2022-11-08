import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MaxSpend {
    protected String category;
    protected int sum;

    public MaxSpend(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public void saveJSON() throws IOException {
        File fileJSON = new File("JSONSpend");
        Gson gson = new Gson();
        try (FileWriter file = new FileWriter(String.valueOf(fileJSON))) {
            file.write(gson.toJson(this));
        }
    }
}
