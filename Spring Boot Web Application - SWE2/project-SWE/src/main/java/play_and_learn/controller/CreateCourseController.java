package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import play_and_learn.model.Course;
import play_and_learn.service.AuthenticationService;
import play_and_learn.service.CourseService;
import play_and_learn.service.UserService;

@Controller
public class CreateCourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
    private AuthenticationService authService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/createcourse")
	public String openCreateGame(Model model) {	
		
		// check if there is no logged in user
		if (userService.getLoggedInUser() ==null) {
			model.addAttribute("error", "Please create a teacher account then log in using it.");
			return "unauthorized-access";
		}		
		
		model.addAttribute("course", new Course());
		return "createcourse";
	}
	
	@PostMapping("/createcourse")
	public String createGame(@ModelAttribute("course") Course course, Model model) {
		
		course.setCreatorTeacherUsername(userService.getLoggedInUser());
		courseService.saveCourse(course);
		
		return "redirect:/course?courseID=" + course.getCourseId();
	}
}
