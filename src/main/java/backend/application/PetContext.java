package backend.application;

import java.util.HashMap;
import java.util.Map;

public class PetContext {

    private static Map <String, String> context = new HashMap<>();

    public static String getValue(String value){
        return context.get(value);
    }
    public static void setValue(String key, String value){
        context.put(key, value);
    }

}
