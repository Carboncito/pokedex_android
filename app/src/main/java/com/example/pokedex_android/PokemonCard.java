package com.example.pokedex_android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.pokedex_android.Services.BasicPokemon;
import com.example.pokedex_android.Services.Http;

import java.util.ArrayList;

public class PokemonCard extends BaseAdapter {
    Activity activity;
    ArrayList<BasicPokemon> pokemons;

    public PokemonCard(Activity activity, ArrayList<BasicPokemon> pokemons) {
        this.activity = activity;
        this.pokemons = pokemons;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView img;
        view = LayoutInflater.from(activity).inflate(R.layout.card, viewGroup, false);
        img = view.findViewById(R.id.img_card);
        Bitmap image = pokemons.get(position).getImage();

        if (image == null) {
            System.out.println("Requesting...");
            String url = "https://img.pokemondb.net/artwork/large/"+pokemons.get(position).getName()+".jpg";
            Http http = new Http(activity);
            http.getImage(url, new Http.VolleyCallBackImage() {
                @Override
                public void onSuccess(Bitmap response) {
                    pokemons.get(position).setImage(response);
                    img.setImageBitmap(response);
                }
                @Override
                public void onError(VolleyError volleyError) {
                    Log.e("Error", "Loading image :(", volleyError);
                }
            });
        } else {
            img.setImageBitmap(image);
        }

        img.setImageBitmap(pokemons.get(position).getImage());
        return view;
    }
}
