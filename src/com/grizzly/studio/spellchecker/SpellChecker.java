package com.grizzly.studio.spellchecker;

import com.grizzly.studio.spellchecker.api.*;

import java.util.*;

/**
 * Created by Norg on 27.05.2016.
 */
public class SpellChecker {
    ResponseApiCaller caller = new YandexResponseApiCaller(new RawYandexApiCaller());
    String text;
    int options = 0;
    Set<ApiResponse> results = new HashSet<>();
    Map<String, List<String>> result;

    public SpellChecker(String text, int options) {
        this.text = text;
        this.options = options;
    }

    public SpellChecker(String text) {
        this.text = text;
        check();
    }

    private void check() {
        Map<String, String> param = new HashMap<>();
        param.put("text", text);
        results = caller.getResponse(param, options);
    }

    public Set<ApiResponse> mistakes() {
        return results;
    }

    public int errCount() {
        return results.size();
    }
}
