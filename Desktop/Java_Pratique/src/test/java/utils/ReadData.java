package utils;

import Model.Client;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadData {

    private final String fileName = "src/test/resources/DataClients.json";

    public Client readDataFromJson(String clientKey) {

        try {
            String content = Files.readString(Path.of(fileName));
            JSONObject root = new JSONObject(content);

            if (!root.has(clientKey)) {
                throw new RuntimeException("Client non trouvé : " + clientKey);
            }

            JSONObject c = root.getJSONObject(clientKey);

            return new Client(
                    c.getString("email"),
                    c.getString("name"),
                    c.getString("phone"),
                    c.getString("adresse"),
                    c.getString("message"),
                    c.getString("produit")
            );

        } catch (IOException e) {
            throw new RuntimeException("Erreur lecture JSON", e);
        }
    }
}
