package com.example.pokedex.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.adapter.PokemonAdapter;
import com.example.pokedex.comm.HTTPSWebUtilDomi;
import com.example.pokedex.dto.PokemonDTO;
import com.example.pokedex.dto.Trainer;
import com.example.pokedex.model.Mappers;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonRef;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,
        PokemonAdapter.OnItemClickListener, View.OnKeyListener {

    private Trainer trainer;
    private ArrayList<PokemonRef> pokemonList;

    private EditText catchET, findET;
    private Button catchBtn, findBtn;

    private FirebaseFirestore db;
    
    private RecyclerView pokemonViewList;
    private PokemonAdapter adapter;
    private LinearLayoutManager layoutManager;

    private ListenerRegistration subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        trainer = (Trainer) getIntent().getExtras().getSerializable("trainer");

        pokemonList = new ArrayList<PokemonRef>();
        
        pokemonViewList = findViewById(R.id.pokemonViewList);

        catchET = findViewById(R.id.catchET);
        findET = findViewById(R.id.findET);
        catchBtn = findViewById(R.id.catchBtn);
        findBtn = findViewById(R.id.findBtn);

        findET.setOnKeyListener(this);

        catchBtn.setOnClickListener(this);
        findBtn.setOnClickListener(this);
        
        pokemonViewList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        pokemonViewList.setLayoutManager(layoutManager);
        
        adapter = new PokemonAdapter(this);
        adapter.setListener(this);
        pokemonViewList.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        
        subscribeToPokemon();
    }

    private void subscribeToPokemon() {
        Query pokemonReference = db.collection("trainers")
                .document(trainer.getId())
                .collection("pokemon")
                .orderBy("timestamp", Query.Direction.DESCENDING);

        subscription = pokemonReference.addSnapshotListener(
                (data, error) -> {
                    pokemonList.clear();
                    adapter.clearPokemon();
                    for(DocumentSnapshot doc : data.getDocuments()){
                        PokemonRef pokemonRef = doc.toObject(PokemonRef.class);
                        pokemonList.add(pokemonRef);
                    }
                    adapter.setPokemon(pokemonList);
                }
        );
    }

    private void searchPokemon(String input) {
        pokemonList.clear();
        adapter.clearPokemon();

        Query pokemonReference = db.collection("trainers")
                .document(trainer.getId())
                .collection("pokemon")
                .whereEqualTo("name", input)
                .orderBy("timestamp", Query.Direction.DESCENDING);

        pokemonReference.get().addOnCompleteListener(
                task -> {
                    if(task.isSuccessful()){
                        if(task.getResult().size() > 0){
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                PokemonRef pokemon = doc.toObject(PokemonRef.class);
                                pokemonList.add(pokemon);
                            }
                            adapter.setPokemon(pokemonList);
                        } else {
                            Toast.makeText(this, "Usted no tiene un Pokemon " +
                                    "con ese nombre", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        subscription.remove();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.catchBtn:
                String name = catchET.getText().toString();
                name = name.toLowerCase().replaceAll(" ", "-");
                HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                Gson gson = new Gson();
                String finalName = name;
                new Thread(
                        () -> {
                            String json = utilDomi
                                    .GETrequest("https://pokeapi.co/api/v2/pokemon/"
                                            + finalName);
                            PokemonDTO dto = gson.fromJson(json, PokemonDTO.class);
                            if(dto != null){
                                Pokemon pokemon = Mappers.fromPokemonDTO(dto);
                                pokemon.setTrainerId(trainer.getId());

                                // Register Pokemon into Database
                                db.collection("pokemon")
                                        .document(pokemon.getId())
                                        .set(pokemon);

                                // Reference Pokemon in Trainer's Pokemon List
                                db.collection("trainers")
                                        .document(trainer.getId())
                                        .collection("pokemon")
                                        .document(pokemon.getId())
                                        .set(Mappers.toPokemonRef(pokemon));
                            } else {
                                runOnUiThread(
                                        () -> {
                                            Toast.makeText(this, "No se encontro " +
                                                    "Pokemon con ese nombre, intente nuevamente.",
                                                    Toast.LENGTH_LONG)
                                                    .show();

                                        }
                                );
                            }
                        }
                ).start();
                break;
            case R.id.findBtn:
                String input = findET.getText().toString();
                input = input.trim().toLowerCase().replaceAll(" ", "-");
                searchPokemon(input);
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("trainer", trainer);
        PokemonRef ref = pokemonList.get(position);
        Query query = db.collection("pokemon")
                .whereEqualTo("id", ref.getId());
        query.get().addOnCompleteListener(
                task -> {
                    if(task.isSuccessful()){
                        if(task.getResult().size() > 0){
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                Pokemon pokemon = doc.toObject(Pokemon.class);
                                i.putExtra("pokemon", pokemon);
                                startActivity(i);
                            }
                        }
                    }
                }
        );
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(v.getId() == R.id.findET){
            if(findET.getText().toString().isEmpty()){
                subscribeToPokemon();
            } else {
                subscription.remove();
            }
        }
        return true;
    }

}