package com.grizzly.studio.spellchecker.api;

import java.util.Map;
import java.util.Set;

/**
 * Created by Norg on 28.05.2016.
 */
public interface ResponseApiCaller{
    RawApiCaller getRawApiCaller();
    Set<ApiResponse> getResponse(Map<String, String> parameters, int options);
}
