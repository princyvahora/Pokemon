package com.example.pokemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Pokeadapter extends RecyclerView.Adapter<Pokeadapter.ViewHolder> {

    private ArrayList<Pokemon> pokearray;
    private Context c;
    private View.OnClickListener pokelistner;


    public Pokeadapter(ArrayList<Pokemon> pokearray, Context c) {
        this.pokearray = pokearray;
        this.c = c;
    }

    public void setOnItemClickListner(View.OnClickListener itemClickListner)
    {
        pokelistner = itemClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(pokearray.get(position).getImage()).into(holder.poke_img);
        holder.poke_name.setText(pokearray.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return pokearray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView poke_img;
        TextView poke_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poke_img = itemView.findViewById(R.id.poke_image);
            poke_name = itemView.findViewById(R.id.poke_name);

            itemView.setTag(this);
            itemView.setOnClickListener(pokelistner);



        }
    }

}
