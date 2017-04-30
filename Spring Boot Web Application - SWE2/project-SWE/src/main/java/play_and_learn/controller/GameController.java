package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.model.Course;
import play_and_learn.model.Game;
import play_and_learn.service.AuthenticationService;
import play_and_learn.service.CourseService;
import play_and_learn.service.UserService;

@Controller
public class GameController {
	@Autowired
	private CourseService courseService;
	@Autowired
    private AuthenticationService authService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/game")
	String openGame(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		Course course = courseService.findByID(courseID);
		Game game = course.getGameByID(gameID);
		
		model.addAttribute("currentLoggedInUser", userService.getLoggedInUser());
		model.addAttribute("courseID", courseID);
		model.addAttribute("game", game);
		
		return "game";
	}
	
	
	@PostMapping("/game")
	String evaluateUserAnswers(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		Course course = courseService.findByID(courseID);
		Game game = course.getGameByID(gameID);
		
		model.addAttribute("currentLoggedInUser", userService.getLoggedInUser());
		model.addAttribute("courseID", courseID);
		model.addAttribute("game", game);
		
		return "game";
	}
	

}