package com.example.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {

    // Trainer-related data
    private String id;
    private long timestamp;
    private String trainerId;

    // PokeAPI data
    private int pokedexId;
    private String name;
    private List<String> type;
    private String sprite;
    private int hp;
    private int atk;
    private int def;
    private int spAtk;
    private int spDef;
    private int speed;

    public Pokemon() {
    }

    public Pokemon(String id, long timestamp, int pokedexId, String name, List<String> type,
                   String sprite, int hp, int atk, int def, int spAtk, int spDef, int speed) {
        this.id = id;
        this.timestamp = timestamp;
        this.pokedexId = pokedexId;
        this.name = name;
        this.type = type;
        this.sprite = sprite;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(int pokedexId) {
        this.pokedexId = pokedexId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static String translateType(String type){
        String output = "";
        switch(type){
            case "normal":
                output = "Normal";
                break;
            case "fire":
                output = "Fuego";
                break;
            case "water":
                output = "Agua";
                break;
            case "grass":
                output = "Planta";
                break;
            case "electric":
                output = "Eléctrico";
                break;
            case "ice":
                output = "Hielo";
                break;
            case "fighting":
                output = "Lucha";
                break;
            case "poison":
                output = "Veneno";
                break;
            case "ground":
                output = "Tierra";
                break;
            case "flying":
                output = "Volador";
                break;
            case "psychic":
                output = "Psiquico";
                break;
            case "bug":
                output = "Bicho";
                break;
            case "rock":
                output = "Roca";
                break;
            case "ghost":
                output = "Fantasma";
                break;
            case "dragon":
                output = "Dragón";
                break;
            case "dark":
                output = "Siniestro";
                break;
            case "steel":
                output = "Acero";
                break;
            case "fairy":
                output = "Hada";
                break;
        }
        return output;
    }

    public static String capitalize(String name){
        if(name.contains("-")){
            String[] split = name.split("-");
            return split[0].substring(0,1).toUpperCase() + split[0].substring(1) + " "
                    + split[1].substring(0,1).toUpperCase() + split[1].substring(1);
        } else {
            return name.substring(0,1).toUpperCase() + name.substring(1);
        }
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }


}
