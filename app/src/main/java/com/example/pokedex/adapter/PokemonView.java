package com.example.pokedex.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.model.PokemonRef;

public class PokemonView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ConstraintLayout root;
    private PokemonRef pokemon;

    private ImageView pokemonImageView;
    private TextView pokemonNameTV;

    PokemonAdapter.OnItemClickListener listener;


    public PokemonView(ConstraintLayout root, PokemonAdapter.OnItemClickListener listener){
        super(root);
        this.root = root;
        pokemonImageView = root.findViewById(R.id.pokemonImageView);
        pokemonNameTV = root.findViewById(R.id.pokemonNameTV);
        this.listener = listener;
        root.setOnClickListener(this);
    }

    public ImageView getPokemonImageView() {
        return pokemonImageView;
    }

    public TextView getPokemonNameTV() {
        return pokemonNameTV;
    }

    public void setPokemon(PokemonRef pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(getAdapterPosition());
    }
}
