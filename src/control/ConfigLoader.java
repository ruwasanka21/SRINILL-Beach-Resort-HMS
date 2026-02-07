/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author ASUS
 */
import com.google.gson.Gson;
import java.io.FileReader;
import model.AppConfig;

public class ConfigLoader {

    private static AppConfig config;

    public static AppConfig loadConfig() {
        if (config == null) {
            try {
                Gson gson = new Gson();
                FileReader reader = new FileReader("config.json");
                config = gson.fromJson(reader, AppConfig.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return config;
    }
}

