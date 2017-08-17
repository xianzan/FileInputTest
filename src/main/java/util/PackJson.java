package util;

import com.google.gson.Gson;
import entity.Process;
import entity.RequestBody;

public class PackJson {
    public static String setjson(RequestBody requestBody){
        Gson gson = new Gson();
        return gson.toJson(requestBody);
    }
}
