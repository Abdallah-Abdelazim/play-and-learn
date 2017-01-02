package database;

import java.io.*;
import java.util.ArrayList;

import ui.Main;
import user.Teacher;

public abstract class DBTeacher {
    private static ArrayList<Teacher> teachers = new ArrayList<>();

    public static void storeData(){
        File inputDataFile = new File ("./AppData/Teachers.ser");
        try {
            FileOutputStream fout = new FileOutputStream(inputDataFile, false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(teachers);
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

        File inputDataFile = new File ("./AppData/Teachers.ser");

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
            teachers = (ArrayList<Teacher>)ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTeacher(Teacher teacher){
        teachers.add(teacher);
        storeData();
    }

    public static boolean verify(String username, String password){
        for (int i=0;i<teachers.size();i++){
            if (teachers.get(i).getAccount().getUsername().equals(username)
                    && teachers.get(i).getAccount().getPassword().equals(password)){
                Main.activeUserIndex = i;
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Teacher> getTeachers(){
        return teachers;
    }
}
