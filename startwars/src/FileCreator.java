import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileCreator {
    public void createjson(List movies, Gson gson) throws IOException {
        FileWriter writeTitles = new FileWriter("titles.json");
        writeTitles.write(gson.toJson(movies));
        writeTitles.close();
    }
}
