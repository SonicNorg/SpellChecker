package com.grizzly.studio.spellchecker.test;

import com.grizzly.studio.spellchecker.SpellChecker;
import com.grizzly.studio.spellchecker.api.ApiResponse;
import java.util.Set;

/**
 * Created by Norg on 27.05.2016.
 */
public class TestMain {
    public static void main(String[] args) {
        SpellChecker checker = new SpellChecker("отакуе");
        Set<ApiResponse> res = checker.mistakes();
        System.out.println(res);
    }
}
