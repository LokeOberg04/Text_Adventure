import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static JsonObject[] arrObj;

    public static void main(String[] args) throws IOException, ParseException {
        String textNodes = "src/textNodes.json";
        Gson gson = new Gson();
        arrObj = gson.fromJson(new FileReader(textNodes), JsonObject[].class);
        showTextNode(0);
    }

    public static void showTextNode(int textNodeId) {
        JsonObject textNode = arrObj[textNodeId];
        System.out.println("\"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" + "\n" + textNode.get("text"));
        JsonArray options = arrObj[textNodeId].get("options").getAsJsonArray();
        for (int i = 0; i < options.size(); i++) {
            System.out.println("Option " + (i + 1) + ": " + options.get(i).getAsJsonObject().get("text"));
        }
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Which option do you choose? (1/2)");
        int answer = keyboard.nextInt();
        if (answer > options.size()) {
            answer = options.size();
        }
        JsonObject pickedOption = (JsonObject) options.get(answer - 1);
        answer = pickedOption.get("nextText").getAsInt();
        for (int i = 0; i < arrObj.length; i++) {
            if (arrObj[i].get("id").getAsInt() == answer) {
                answer = i;
                i = arrObj.length;
            }
        }
        showTextNode(answer);
    }
}