package com.grizzly.studio.spellchecker.api;

import java.util.Map;
import org.json.*;

/**
 * Created by Norg on 27.05.2016.
 */
public interface RawApiCaller {
    JSONArray call(Map<String, String> parameters, int options);
}
