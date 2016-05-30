package com.grizzly.studio.spellchecker.api;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Norg on 27.05.2016.
 */
public class RawYandexApiCaller implements RawApiCaller {
    public final String address = "http://speller.yandex.net/services/spellservice.json/checkText";

    @Override
    public JSONArray call(Map<String, String> parameters, int options) {
        JSONArray json = null;
        try {
            json = new JSONArray(getRequest(parameters, options));
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        return json;
    }

    private String getRequest(Map<String, String> parameters, int options) throws Exception{
        String answer;
        StringBuilder urlString = new StringBuilder(address);
        if (!parameters.isEmpty()) {
            urlString.append("?");

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                urlString.append(urlString.charAt(urlString.length()-1)=='?'?"":"&");
                urlString.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                urlString.append("=");
                urlString.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            urlString.append("&options=");
            urlString.append(options);
        }

        URL url = new URL(urlString.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int respCode = conn.getResponseCode();
        if (respCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            answer = response.toString();
        }else {
            answer = conn.getResponseMessage();
        }
        return StringEscapeUtils.unescapeJava(answer);
    }
}
