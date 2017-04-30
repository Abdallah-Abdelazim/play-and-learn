package play_and_learn;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import play_and_learn.model.Course;
import play_and_learn.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class Testcourse extends AbstractTestNGSpringContextTests {
	@Autowired
	CourseService courseservice ;
	
	@DataProvider(name="courses1")
	public static Object[][] courses1(){
		return new Object [][] {{"math", "math course"}, { "zzzz", "hahaha"}
		, { "prolog", "I hate you language"} , { "c++", "ohhhh"}};
	}
	@Test(dataProvider="courses1")
  public void f(String name, String description) {
	 
	  Course course = new Course(name, description);

	  int n=courseservice.getAllCourses().size();
	  
	 
	  courseservice.saveCourse(course);
	  n++;
	  
	  long c1= courseservice.getAllCourses().size();
	  Assert.assertEquals(n, c1);
  }
	
	@DataProvider(name="courses2")
	public static Object[][] courses2(){
		return new Object [][] {{"matlab", "matlab course"}, { "aaaa", "hahaha"}
		, { "lisp", "I hate you language"} , { "c", "ohhhh"}};
	}
	@Test(dataProvider="courses2")
	  public void f1(String name, String description) {
		 
		  Course course =new Course(name, description);	
		  
		  courseservice.saveCourse(course);
		  
		  Assert.assertEquals(course.getCourseName() ,courseservice.findByName(name).getCourseName());
	  }
	
}
