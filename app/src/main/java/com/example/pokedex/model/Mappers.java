package com.example.pokedex.model;

import com.example.pokedex.dto.PokemonDTO;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Mappers {

    public static Pokemon fromPokemonDTO(PokemonDTO dto){
        if(dto == null){
            return null;
        }

        int pokedexId = dto.getId();
        String name = dto.getName();
        List<String> type = new ArrayList<String>();
        type.add(dto.getTypes()[0].getType().getName());
        if(dto.getTypes().length > 1) type.add(dto.getTypes()[1].getType().getName());
        String sprite = dto.getSprites().getFront_default();

        // Stats
        int hp = dto.getStats()[0].getBase_stat();
        int atk = dto.getStats()[1].getBase_stat();
        int def = dto.getStats()[2].getBase_stat();
        int spAtk = dto.getStats()[3].getBase_stat();
        int spDef = dto.getStats()[4].getBase_stat();
        int speed = dto.getStats()[5].getBase_stat();

        String id = UUID.randomUUID().toString();
        long timestamp = new Date().getTime();

        return new Pokemon(id, timestamp, pokedexId, name, type, sprite, hp, atk, def, spAtk,
                spDef, speed);

    }

    public static PokemonRef toPokemonRef(Pokemon pokemon){
        if(pokemon == null) return null;

        String id = pokemon.getId();
        String name = pokemon.getName();
        long timestamp = pokemon.getTimestamp();
        String sprite = pokemon.getSprite();

        return new PokemonRef(id, name, timestamp, sprite);
    }

}
