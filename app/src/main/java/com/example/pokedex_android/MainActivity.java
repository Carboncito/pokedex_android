package com.example.pokedex_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pokedex_android.Services.PokemonService;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.pokedex_android.MESSAGE";
    public PokemonService pokemonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onInitListeners();

        Log.d("Fetching: ", "Init Pokemons");
        pokemonService = new PokemonService(this);
    }

    private void onInitListeners() {
        LinearLayout viewSearcher = findViewById(R.id.view_searcher);
        EditText searcher = findViewById(R.id.searcher);
        TextView cancelButton = findViewById(R.id.txt_cancel_search);
        ListView pokemonList = findViewById(R.id.pokemon_list);

        searcher.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cancelButton.setVisibility(View.VISIBLE);
                } else {
                    cancelButton.setVisibility(View.GONE);
                }
                viewSearcher.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
                viewSearcher.getLayoutTransition().setDuration(350);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null) {
                    InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                searcher.clearFocus();
                cancelButton.setVisibility(View.GONE);
            }
        });

        pokemonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "title", Toast.LENGTH_SHORT).show();
            }
        });
    }
}