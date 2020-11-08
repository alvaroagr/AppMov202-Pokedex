package com.example.pokedex.model;

public class PokemonRef {

    private String id;
    private String name;
    private long timestamp;
    private String sprite;

    public PokemonRef() {
    }

    public PokemonRef(String id, String name, long timestamp, String sprite) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.sprite = sprite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
