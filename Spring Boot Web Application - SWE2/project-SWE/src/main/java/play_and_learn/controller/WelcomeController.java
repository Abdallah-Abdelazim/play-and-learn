package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import play_and_learn.service.CourseService;
import play_and_learn.service.UserService;

@Controller
public class WelcomeController {	
	@Autowired
    private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/welcome")
	public String openWelcomePage(Model model) {
		// check if there is no logged in user
		if (userService.getLoggedInUser() ==null) {
			model.addAttribute("error", "Please create a teacher account then log in using it.");
			return "unauthorized-access";
		}	
		
		model.addAttribute("user", userService.findByUsername(userService.getLoggedInUser()));
		model.addAttribute("courses", courseService.getAllCourses());
		
		return "welcome";
	}
	
}
