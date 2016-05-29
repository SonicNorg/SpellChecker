package com.grizzly.studio.spellchecker.api;

import java.util.List;

/**
 * Created by Norg on 28.05.2016.
 */
public interface ApiResponse {
    YandexApiResponse addToDictionary(String s);
    List<String> dictionary();
}
