package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //private static TextView sandwich_name = null;
    private static TextView origin_tv = null;
    private static TextView description_tv = null;
    private static TextView ingredients_tv = null;
    private static TextView also_known_tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView image_iv = findViewById(R.id.image_iv);
        origin_tv = findViewById(R.id.origin_tv);
        description_tv = findViewById(R.id.description_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        also_known_tv = findViewById(R.id.also_known_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(image_iv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //sandwich_name.setText(sandwich.getMainName());
        this.origin_tv.setText(sandwich.getPlaceOfOrigin());

        this.description_tv.setText(sandwich.getDescription());

        List<String> list = sandwich.getAlsoKnownAs();
        for(String name: list) {
            also_known_tv.append(name + ", ");
        }

        list = sandwich.getIngredients();
        for(String name: list) {
            ingredients_tv.append(name + "\n");
        }

    }
}
