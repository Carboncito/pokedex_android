package com.example.pokedex_android.Services;

import android.graphics.Bitmap;

public class BasicPokemon {
    private String name;
    private String url;
    private String pokemonId;
    private Bitmap image;

    public BasicPokemon(String _name, String _url) {
        String[] urlSplitted = _url.split("/");

        name = _name;
        url = _url;
        pokemonId = urlSplitted[urlSplitted.length - 1];
    }

    public void setImage(Bitmap image) { this.image = image; }
    public String getName() { return name; }
    public Bitmap getImage() { return image; }
    public String getUrl() { return url; }
    public String getPokemonId() { return pokemonId; }
}
