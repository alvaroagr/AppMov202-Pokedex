package com.example.pokedex.dto;

public class PokemonStatDTO {

    private int base_stat;
    private Stat stat;

    public PokemonStatDTO() {
    }

    public PokemonStatDTO(int base_stat, Stat stat) {
        this.base_stat = base_stat;
        this.stat = stat;
    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
