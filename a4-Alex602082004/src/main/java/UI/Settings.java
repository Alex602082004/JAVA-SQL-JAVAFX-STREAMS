package UI;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private String repo_type;
    private String start;

    private static Settings instance;

    private Settings() {
    }

    public String getRepoType() {
        return repo_type;
    }

    public String getStart() {
        return start;
    }

    public static Settings getInstance() {
        if (instance == null) {
            // Citim fisierul de setari -- asta ruleaza o singura data
            Properties settings = new Properties();
            try {
                settings.load(new FileReader("C:\\Users\\alexp\\IdeaProjects\\lab4\\settings.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            instance = new Settings();
            instance.repo_type = settings.getProperty("repo_type");
            instance.start = settings.getProperty("start");
        }
        return instance;
    }
}
