package com.grizzly.studio.spellchecker.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Norg on 28.05.2016.
 */
public class YandexResponseApiCaller implements ResponseApiCaller {
    private final RawApiCaller rawYandexApiCaller;

    public YandexResponseApiCaller(RawYandexApiCaller rawYandexApiCaller) {
        this.rawYandexApiCaller = rawYandexApiCaller;
    }

    public RawApiCaller getRawApiCaller() {
        return rawYandexApiCaller;
    }

    @Override
    public Set<ApiResponse> getResponse(Map<String, String> parameters, int options) {
        Set<ApiResponse> result = new HashSet<>();
        Iterator<Object> iter = rawYandexApiCaller.call(parameters, options).iterator();
        while(iter.hasNext()) {
            JSONObject wrong = (JSONObject)iter.next();
            ApiResponse response = new YandexApiResponse(wrong.getString("word"), wrong.getInt("code"));
            Iterator<Object> iterWords = wrong.getJSONArray("s").iterator();
            while (iterWords.hasNext())
                response.addToDictionary(iterWords.next().toString());
            result.add(response);
        }
        return result;
    }

    public JSONArray call(Map<String, String> parameters, int options) {
        return rawYandexApiCaller.call(parameters, options);
    }
}
