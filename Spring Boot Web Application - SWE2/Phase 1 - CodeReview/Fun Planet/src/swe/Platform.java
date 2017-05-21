package swe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Platform {
	Scanner x = new Scanner(System.in);
	
	public Platform() {
		super();
	}

	public void Start() throws FileNotFoundException, IOException{
            Account_DB DB = new Account_DB();
            DB.readfile();
            while(true){
		System.out.println("1) Registration");
		System.out.println("2) Login");
                System.out.println("3) Exit");
		System.out.println("your Choise ->");
		int s = x.nextInt();
		if(s == 2){
			//x.next();
			Login L = new Login();
			L.Show();
		}
		if(s== 1)
		{
			Register R = new Register();
			R.Show();
			
		}
                if(s==3)
                {
                    DB.writeFile();
                    System.exit(0);
                }
                }
	}
}
