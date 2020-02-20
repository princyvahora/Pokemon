package com.example.pokemon;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PokeDesc extends AppCompatActivity {

    ImageView img_poke;
    TextView txt_name,txt_type,txt_ability,txt_height,txt_weight,txt_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_desc);


        img_poke = findViewById(R.id.desc_img);
        txt_name = findViewById(R.id.desc_name);
        txt_type = findViewById(R.id.desc_type);
        txt_ability = findViewById(R.id.desc_ability);
        txt_height = findViewById(R.id.desc_height);
        txt_weight = findViewById(R.id.desc_weight);
        txt_desc = findViewById(R.id.desc_description);


        Intent i = getIntent();

        Pokemon pokemon = i.getParcelableExtra("data");


        Picasso.get().load(pokemon.getImage()).into(img_poke);

        txt_name.setText(pokemon.getName());
        txt_type.setText(pokemon.getType());
        txt_desc.setText(pokemon.getDesc());
        txt_ability.setText(pokemon.getAbility());
        txt_height.setText("Height :"+pokemon.getHeight());
        txt_weight.setText("Weight :"+pokemon.getWeight());

        txt_desc.setMovementMethod(new ScrollingMovementMethod());

    }
}
