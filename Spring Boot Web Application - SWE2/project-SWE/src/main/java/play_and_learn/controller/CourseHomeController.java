package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.model.Course;
import play_and_learn.service.CourseService;
import play_and_learn.service.UserService;

@Controller
public class CourseHomeController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/course")
	public String openCourseHome(@RequestParam(value="courseID", required=true) int courseID
			, Model model) {
		
		// check if there is no logged in user
		if (userService.getLoggedInUser() == null) {
			model.addAttribute("error", "Please create an account then log in using it.");
			return "unauthorized-access";
		}	
		
		Course course = courseService.findByID(courseID);
		
		model.addAttribute("currentLoggedInUser", userService.getLoggedInUser());
		model.addAttribute("course", course);
		return "course";
	}
	

}
