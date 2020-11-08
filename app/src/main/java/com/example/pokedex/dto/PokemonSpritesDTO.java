package com.example.pokedex.dto;

public class PokemonSpritesDTO {

    private String front_default;

    public PokemonSpritesDTO() {
    }

    public PokemonSpritesDTO(String front_default) {
        this.front_default = front_default;
    }

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }
}
