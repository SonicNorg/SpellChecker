package com.grizzly.studio.spellchecker.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Norg on 28.05.2016.
 */
public class YandexApiResponse implements ApiResponse {
    final String word;
    final int errorCode;
    final String errorMessage;
    private final List<String> dictionary = new ArrayList<>();

    public YandexApiResponse(String word, int errorCode) {
        this.word = word;
        this.errorCode = errorCode;
        switch (errorCode) {
            case 1:
                this.errorMessage = "Нет в словаре";
                break;
            case 2:
                this.errorMessage = "Повтор";
                break;
            case 3:
                this.errorMessage = "Неверное употребление прописных и строчных букв";
                break;
            case 4:
                this.errorMessage = "Текст содержит слишком много ошибок";
                break;
            default:
                this.errorMessage = "Ошибка";
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
            result.append("; ");
            Iterator iter = dictionary.iterator();
            while (iter.hasNext()) {
                result.append(iter.next());
                if (iter.hasNext())
                    result.append(", ");
            }
        }
        return result.toString().trim();
    }
}
