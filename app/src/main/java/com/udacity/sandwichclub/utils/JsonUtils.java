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

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(jsonObject.getJSONObject("name").getString("mainName"));

            List<String> list = new ArrayList<String>();
            JSONArray jsonArray = jsonObject.getJSONObject("name").getJSONArray("alsoKnownAs");
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i=0; i<len; i++){
                    list.add(jsonArray.get(i).toString());
                }
            }
            sandwich.setAlsoKnownAs(list);
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
            sandwich.setDescription(jsonObject.getString("description"));
            sandwich.setImage(jsonObject.getString("image"));
            list = new ArrayList<String>();
            jsonArray = jsonObject.getJSONArray("ingredients");
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
