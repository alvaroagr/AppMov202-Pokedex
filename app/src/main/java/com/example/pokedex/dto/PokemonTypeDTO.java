package com.example.pokedex.dto;

public class PokemonTypeDTO {

    private Type type;

    public PokemonTypeDTO() {
    }

    public PokemonTypeDTO(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
