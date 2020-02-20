package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pokemon> pokelst;
    Pokeadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokelst = new ArrayList<>();
        String link = "https://next.json-generator.com/api/json/get/E14trR2lD";

        try {

            String data = new Ayscdata().execute(link).get();
            System.out.println("Data From Main :"+data);

            JSONObject mainObj = new JSONObject(data);
            JSONArray pokearray = mainObj.getJSONArray("Pokemon");

            for (int i=0;i<pokearray.length();i++)
            {
                JSONObject child = pokearray.getJSONObject(i);

                String name = child.getString("name");
                String img = child.getString("image");
                String type = child.getString("type");
                String ability = child.getString("ability");
                String height = child.getString("height");
                String weight = child.getString("weight");
                String desc = child.getString("description");

                pokelst.add(new Pokemon(name,img,type,ability,height,weight,desc));

            }

            adapter = new Pokeadapter(pokelst,getApplication());
            initView();



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void initView()
    {
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.recycle_poke);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(onItemClickpoke);
    }


    public View.OnClickListener onItemClickpoke = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder= (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();

            Toast.makeText(getApplicationContext(),pokelst.get(position).getName(),Toast.LENGTH_LONG).show();

            Intent i  = new Intent(MainActivity.this,PokeDesc.class);
            i.putExtra("data",pokelst.get(position));
            startActivity(i);


        }
    };
}
