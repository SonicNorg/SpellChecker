package com.grizzly.studio.spellchecker;

import com.grizzly.studio.spellchecker.api.*;

import java.util.*;

/**
 * Created by Norg on 27.05.2016.
 */
public class SpellChecker {
    private final ResponseApiCaller caller = new YandexResponseApiCaller(new RawYandexApiCaller());
    private final String text;
    private final int options;
    private final Set<ApiResponse> results;

    public SpellChecker(String text, int options) {
        this.text = text;
        this.options = options;
        results = check();
    }

    public SpellChecker(String text) {
        this.text = text;
        this.options = 0;
        results = check();
    }

    private Set<ApiResponse> check() {
        Map<String, String> param = new HashMap<>();
        param.put("text", text);
        return caller.getResponse(param, options);
    }

    public Set<ApiResponse> mistakes() {
        return results;
    }

    public int errCount() {
        return results.size();
    }
}
