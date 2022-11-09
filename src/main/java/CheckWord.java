import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;

public class CheckWord {
    public static void checkWord(JsonObject request) {
        String text = String.valueOf(request.get("title"));
        switch (text) {
            case "\"булка\"", "\"колбаса\"", "\"сухарики\"", "\"курица\"" -> {
                System.out.println("Вы потратили на еду: " + Integer.parseInt(String.valueOf(request.get("sum"))));
                Main.base.spendMoneyFood(Integer.parseInt(String.valueOf(request.get("sum"))));
                System.out.println("Осталось финансов на еду: " + Main.base.maxFood.getSum());
                Main.typeRequest = "еда";
            }
            case "\"тапки\"", "\"шапка\"" -> {
                System.out.println("Вы потратили на одежду: " + Integer.parseInt(String.valueOf(request.get("sum"))));
                Main.base.spendMoneyClothes(Integer.parseInt(String.valueOf(request.get("sum"))));
                System.out.println("Осталось финансов на одежду: " + Main.base.maxClothes.getSum());
                Main.typeRequest = "одежда";
            }
            case "\"акции\"" -> {
                System.out.println("Вы потратили на финансы: " + Integer.parseInt(String.valueOf(request.get("sum"))));
                Main.base.spendMoneyFinance(Integer.parseInt(String.valueOf(request.get("sum"))));
                System.out.println("Осталось на финансы: " + Main.base.maxFinance.getSum());
                Main.typeRequest = "финансы";
            }
            default -> {
                System.out.println("Вы потратили на другое: " + Integer.parseInt(String.valueOf(request.get("sum"))));
                Main.base.spendMoneyOther(Integer.parseInt(String.valueOf(request.get("sum"))));
                System.out.println("Осталось финансов на другое: " + Main.base.other.getSum());
                Main.typeRequest = "другое";
            }
        }
        Gson gson = new Gson();
        try (FileWriter file = new FileWriter("Base.json")) {
            file.write(gson.toJson(Main.base));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
