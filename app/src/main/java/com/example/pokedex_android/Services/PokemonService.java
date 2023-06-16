package com.example.pokedex_android.Services;

import android.app.Activity;

import com.android.volley.VolleyError;
import com.example.pokedex_android.Loader;

import org.json.JSONObject;

public class PokemonService extends Http {
    public JSONObject pokemons;
    public VolleyError error;

    public PokemonService(Activity ctx) {
        super(ctx);

        Loader loader = new Loader(ctx);
        loader.startLoader();

        this.get("/pokemon", new Http.VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                pokemons = jsonObject;
                loader.dismissDialog();
            }

            @Override
            public void onError(VolleyError volleyError) {
                error = volleyError;
                loader.dismissDialog();
            }
        });
    }

    public JSONObject getPokemons() {
        return pokemons;
    }
}
