package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.ProjectSweApplication;
import play_and_learn.model.Course;
import play_and_learn.model.Game;
import play_and_learn.service.AuthenticationService;
import play_and_learn.service.CourseService;

@Controller
public class CreateGameController {
	@Autowired
	private CourseService courseService;
	@Autowired
    private AuthenticationService authService;
	
	
	@GetMapping(value = "/creategame")
	public String openCreateGameForm(@RequestParam(value="courseID", required=true) int courseID
			, Model model) {
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("game", new Game());
		return "creategame";
	}
	
	@PostMapping(value = "/creategame")
	public String createGame(@RequestParam(value="courseID", required=true) int courseID, 
			@ModelAttribute("game") Game game, Model model) {
		
		Course course = courseService.findByID(courseID);
		
		game.setCreatorTeacherUsername(ProjectSweApplication.activeUsername);
		game.setCourse(course);
		
		course.addGame(game);
		
		courseService.saveCourse(course);
		
		course = courseService.findByID(courseID);
		
		return "redirect:/game?courseID=" + courseID + "&gameID=" + course.getCourseGames().get(course.getCourseGames().size()-1).getGameId(); // redirects 
	}

}
