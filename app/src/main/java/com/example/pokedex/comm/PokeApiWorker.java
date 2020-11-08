package com.example.pokedex.comm;

public class PokeApiWorker extends Thread {

    private String name;

    public PokeApiWorker(String name){
        this.name = name;
    }
}
