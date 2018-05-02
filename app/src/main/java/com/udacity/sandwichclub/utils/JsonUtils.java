package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JsonUtils {

    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_MAIN_NAME_KEY = "mainName";
    public static final String JSON_ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    public static final String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY = "description";
    public static final String JSON_IMAGE_KEY = "image";
    public static final String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(jsonObject.optJSONObject(JSON_NAME_KEY ).optString(JSON_MAIN_NAME_KEY));

            List<String> list = new ArrayList<String>();
            JSONArray jsonArray = jsonObject.optJSONObject(JSON_NAME_KEY ).optJSONArray(JSON_ALSO_KNOWN_AS_KEY);
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i=0; i<len; i++){
                    list.add(jsonArray.get(i).toString());
                }
            }
            sandwich.setAlsoKnownAs(list);
            sandwich.setPlaceOfOrigin(jsonObject.optString(JSON_PLACE_OF_ORIGIN_KEY));
            sandwich.setDescription(jsonObject.optString(JSON_DESCRIPTION_KEY));
            sandwich.setImage(jsonObject.optString(JSON_IMAGE_KEY));
            list = new ArrayList<String>();
            jsonArray = jsonObject.optJSONArray(JSON_INGREDIENTS_KEY);
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i=0; i<len; i++){
                    list.add(jsonArray.get(i).toString());
                }
            }
            sandwich.setIngredients(list);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
