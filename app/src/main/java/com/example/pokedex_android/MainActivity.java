package com.example.pokedex_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pokedex_android.Services.PokemonService;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.pokedex_android.MESSAGE";
    public PokemonService pokemonService;
    public Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        imageView = (ImageView) activity.findViewById(R.id.pokeball_loader);
        setSupportActionBar(toolbar);

        Log.d("Fetching: ", "Init Pokemons");
        pokemonService = new PokemonService(this);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        System.out.println(message);
        System.out.println(pokemonService.getPokemons());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_group) {
            Toast.makeText (this, "New group clicked!!", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.web_whatsapp) {
            Toast.makeText (this, "Web Whatsapp clicked!!", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}