package game;

import java.io.Serializable;
import java.util.ArrayList;

public class Field  implements Serializable , IField {
    public static int fCount = 0;
    private ArrayList<Game> games = new ArrayList<>();
    private String name;
    private  int id;

    @Override
    public String toString() {
        return "Filed [games=" + games + " name=" + name + ", id=" + id + "]";
    }

    public Field() {
        name="";
        id=fCount++;
    }

    public Field(String name , ArrayList<Game> games) {
        this.name=name;
        id=fCount++;
        this.games=games;
    }

    public static void setFCount(int fCount) {
        Field.fCount = fCount;
    }
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Game> getGames() {
        return games;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void addGame(Game g){
        games.add(g);
    }

    public int search (String gameName){
        for (int i=0;i<games.size();i++){
            if (games.get(i).getName().equals(gameName)){
                return i;
            }
        }
        return -1;
    }
}
