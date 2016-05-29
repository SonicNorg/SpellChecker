package com.grizzly.studio.spellchecker.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Norg on 28.05.2016.
 */
public class YandexApiResponse implements ApiResponse {
    final String word;
    final int errorCode;
    final String errorMessage;
    private List<String> dictionary = new ArrayList<>();

    public YandexApiResponse(String word, int errorCode) {
        this.word = word;
        this.errorCode = errorCode;
        switch (errorCode) {
            case 1:
                this.errorMessage = "Слова нет в словаре";
                break;
            case 2:
                this.errorMessage = "Повтор слова";
                break;
            case 3:
                this.errorMessage = "Неверное употребление прописных и строчных букв";
                break;
            case 4:
                this.errorMessage = "Текст содержит слишком много ошибок";
                break;
            default:
                this.errorMessage = "Ошибка в слове";
        }
    }
    
    public YandexApiResponse addToDictionary(String s) {
        dictionary.add(s);
        return this;
    }

    public List<String> dictionary() {
        return dictionary;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(errorMessage);
        result.append(": ");
        result.append(word);
        if (dictionary().size() > 0) {
            result.append(";");
            for (String s : dictionary) {
                result.append(" ");
                result.append(s);
            }
        }
        return result.toString();
    }
}
