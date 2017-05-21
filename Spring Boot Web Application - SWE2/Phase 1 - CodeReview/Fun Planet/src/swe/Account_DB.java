package swe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.Vector;

public class Account_DB {
	//this class shows the Account database used in our game platform
	public static Vector<Account> a = new Vector<>();
	Account A=  new Account();
	public Account returnAccount(){
		return A;
	}
        public void writeFile() throws IOException{
            String d = "";
            for(int i =0;i<a.size();i++){
                d = d + a.get(i).UserName + "," + a.get(i).Password + "," + a.get(i).Role + ",";
            }
            File filedest = new File("Account.txt");
            Files.write(Paths.get(filedest.getPath()), d.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            //a.clear();
        }
        public void readfile() throws FileNotFoundException{
            Scanner x = new Scanner(new File("Account.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                Account aa = new Account();
                aa.UserName = x.next();
                aa.Password = x.next();
                aa.Role = x.next();
                a.addElement(aa);
            }
            
        }
}
