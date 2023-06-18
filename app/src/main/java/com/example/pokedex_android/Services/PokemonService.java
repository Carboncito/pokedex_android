package com.example.pokedex_android.Services;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.pokedex_android.Loader;
import com.example.pokedex_android.PokemonCard;
import com.example.pokedex_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class PokemonService extends Http {
    private Activity activity;
    private int count;
    private String nextPagination;
    private String previusPagination;
    private ArrayList<BasicPokemon> pokemons = new ArrayList<>();
    private ListView listView;
    public VolleyError error;

    public PokemonService(Activity ctx) {
        super(ctx);

        activity = ctx;
        Loader loader = new Loader(ctx);
        loader.startLoader();

        listView = ctx.findViewById(R.id.pokemon_list);

        this.get("/pokemon", new Http.VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    count = jsonObject.getInt("count");
                    nextPagination = jsonObject.getString("next");
                    previusPagination = jsonObject.getString("previous");
                    JSONArray _pokemons = jsonObject.getJSONArray("results");

                    for (int i = 0; i < _pokemons.length(); i++) {
                        JSONObject pokemon = _pokemons.getJSONObject(i);
                        BasicPokemon basicPokemon = new BasicPokemon(
                                pokemon.getString("name"),
                                pokemon.getString("url"));

                        pokemons.add(basicPokemon);
                    }

                    PokemonCard adapter = new PokemonCard(ctx, pokemons);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    Log.e("Error", "unexpected JSON exception", e);
                }
                loader.dismissDialog();
            }

            @Override
            public void onError(VolleyError volleyError) {
                error = volleyError;
                loader.dismissDialog();
            }
        });
    }

    public ArrayList<BasicPokemon> getPokemons() { return pokemons; }
    public int getCount() { return count; }
    public String getNextPagination() { return nextPagination; }
    public String getPreviusPagination() { return previusPagination; }
}
