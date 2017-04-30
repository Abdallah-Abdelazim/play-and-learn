package play_and_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectSweApplication {
	
	public static String activeUsername = null;
	public static String activePassword = null;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectSweApplication.class, args);
	}
}
