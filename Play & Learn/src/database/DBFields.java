package database;

import java.io.*;
import java.util.ArrayList;

import game.Field;
import game.Game;

public abstract class DBFields {
    private static ArrayList<Field> fields = new ArrayList<>();

    public static void storeData(){
        File inputDataFile = new File ("./AppData/Fields.ser");
        try {
            FileOutputStream fout = new FileOutputStream(inputDataFile, false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(fields);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loadData(){
        // handles when the directory doesn't exist
        File directory = new File ("./AppData");
        if (! directory.exists()){
            directory.mkdir();
        }

        File inputDataFile = new File ("./AppData/Fields.ser");

        // handles when the file isn't created or it's empty
        if (!inputDataFile.exists()){
            try {
                inputDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        else if (inputDataFile.length() == 0){
            return;
        }

        try {
            FileInputStream fin = new FileInputStream(inputDataFile);
            ObjectInputStream ois = new ObjectInputStream(fin);
            fields = (ArrayList<Field>)ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(fields);  // Test

    }

    public static void addField(Field field){
        fields.add(field);
        storeData();
    }

    public static void addGameToField(String fieldName , Game game){
        // if the field already exist then just add the game
        // if not add the field with the game in it
        for (int i=0; i<fields.size() ; i++){
            if (fields.get(i).getName().equals(fieldName)){
                fields.get(i).addGame(game);
                storeData();
            }
        }

        ArrayList<Game> games = new ArrayList<Game>();
        games.add(game);
        Field field = new Field(fieldName , games);
        fields.add(field);
        storeData();
    }

    public static ArrayList<Field> getFields(){
        return fields;
    }

    public static ArrayList<Game> getGamesInField(String fieldName){
        for (int i=0; i<fields.size() ; i++){
            if (fields.get(i).getName().equals(fieldName)){
                return fields.get(i).getGames();
            }
        }
        return null;
    }

    public static Game getGame(String fieldName , String gameName){
        for (int i=0; i<fields.size() ; i++){  // search for field
            if (fields.get(i).getName().equals(fieldName)){
                for (int j = 0; j< fields.get(i).getGames().size() ; j++){ // search for game after finding field
                    if (fields.get(i).getGames().get(j).getName().equals(gameName)){
                        return (fields.get(i).getGames().get(j));
                    }
                }
            }
        }
        return null;
    }


}
