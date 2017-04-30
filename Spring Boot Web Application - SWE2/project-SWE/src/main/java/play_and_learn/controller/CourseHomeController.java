package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.ProjectSweApplication;
import play_and_learn.model.Course;
import play_and_learn.service.AuthenticationService;
import play_and_learn.service.CourseService;

@Controller
public class CourseHomeController {
	@Autowired
	private CourseService courseService;
	@Autowired
    private AuthenticationService authService;
	
	@RequestMapping("/course")
	public String openCourseHome(@RequestParam(value="courseID", required=true) int courseID
			, Model model) {
		
		Course course = courseService.findByID(courseID);
		
		model.addAttribute("currentLoggedInUser", ProjectSweApplication.activeUsername);
		model.addAttribute("course", course);
		return "course";
	}
	

}
