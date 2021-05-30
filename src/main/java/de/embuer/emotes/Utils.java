package de.embuer.emotes;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

public class Utils {

    public static <T> HashMap<T, T> hashMapOf(ImmutableMap<T, T> map) {
        HashMap<T, T> result = new HashMap<>();
        for (T key : map.keySet().asList()) {
            result.put(key, map.get(key));
        }
        return result;
    }

}
