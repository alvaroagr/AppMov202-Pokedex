package com.example.pokedex.dto;

import java.util.Date;

public class PokemonDTO {

    private int id;
    private String name;
    private PokemonStatDTO[] stats;
    private PokemonTypeDTO[] types;
    private PokemonSpritesDTO sprites;

    public PokemonDTO() {
    }

    public PokemonDTO(int id, String name, PokemonStatDTO[] stats, PokemonTypeDTO[] types,
                      PokemonSpritesDTO sprites) {
        this.id = id;
        this.name = name;
        this.stats = stats;
        this.types = types;
        this.sprites = sprites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonStatDTO[] getStats() {
        return stats;
    }

    public void setStats(PokemonStatDTO[] stats) {
        this.stats = stats;
    }

    public PokemonTypeDTO[] getTypes() {
        return types;
    }

    public void setTypes(PokemonTypeDTO[] types) {
        this.types = types;
    }

    public PokemonSpritesDTO getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSpritesDTO sprites) {
        this.sprites = sprites;
    }

}
