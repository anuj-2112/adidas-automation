package backend.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {
    public static Object fromGson(String body, Type type) {
        return new Gson().fromJson(body,type);
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}

