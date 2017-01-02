package database;

import java.io.*;
import java.util.ArrayList;

import ui.Main;
import user.Student;


public abstract class DBStudent {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void storeData(){
        File inputDataFile = new File ("./AppData/Students.ser");
        try {
            FileOutputStream fout = new FileOutputStream(inputDataFile, false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(students);
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

        File inputDataFile = new File ("./AppData/Students.ser");

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
            students = (ArrayList<Student>)ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Student student){
        students.add(student);
        storeData();
    }

    public static boolean verify(String username, String password){
        for (int i=0;i<students.size();i++){
            if (students.get(i).getAccount().getUsername().equals(username)
                    && students.get(i).getAccount().getPassword().equals(password)){
                Main.activeUserIndex = i;
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Student> getStudents(){
        return students;
    }
}
