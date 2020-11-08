package com.example.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonRef;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    private ArrayList<PokemonRef> pokemon;

    private OnItemClickListener listener;

    public PokemonAdapter(OnItemClickListener listener){
        this.pokemon = new ArrayList<PokemonRef>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokemon_row, parent, false);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PokemonView pokemonView = new PokemonView(rowroot, listener);
        return pokemonView;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        holder.setPokemon(pokemon.get(position));
        holder.getPokemonNameTV().setText(Pokemon.capitalize(pokemon.get(position).getName()));
        Glide.with(holder.getPokemonImageView().getContext()).load(pokemon.get(position)
                .getSprite()).centerCrop().into(holder.getPokemonImageView());
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

//    public void addPokemon(Pokemon pokemon){
//        this.pokemon.add(pokemon);
//    }


    public void setPokemon(ArrayList<PokemonRef> pokemon) {
        this.pokemon = pokemon;
        notifyDataSetChanged();
    }

    public void clearPokemon(){
        pokemon.clear();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
