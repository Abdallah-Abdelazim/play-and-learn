package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.model.Course;
import play_and_learn.model.GameChange;
import play_and_learn.model.Question;
import play_and_learn.service.CourseService;
import play_and_learn.service.UserService;

@Controller
public class AddQuestionController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/addquestion")
	public String openQuestionForm(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		// check if there is no logged in user & the user must be a teacher
		if (userService.getLoggedInUser() == null) {
				model.addAttribute("error", "Please create a teacher account then log in using it.");
				return "unauthorized-access";
		}
		else if (!(userService.findByUsername(userService.getLoggedInUser()).getRole().equals("teacher"))) {
			model.addAttribute("error", "You must be a teacher to enter this page.");
			return "unauthorized-access";
		}
		
		Course course = courseService.findByID(courseID);
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("gameID", gameID);
		model.addAttribute("gameType", course.getGameByID(gameID).getGameType());
		model.addAttribute("question", new Question());
		return "addquestion";
	}
	
	@PostMapping("/addquestion")
	public String addQuestion(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, @ModelAttribute("question") Question question, Model model) {
		
		Course course = courseService.findByID(courseID);
		
		question.setGame(course.getGameByID(gameID));
		
		course.getGameByID(gameID).addQuestion(question);
		
		/* Record the game change */
		GameChange gameChange = new GameChange("Add Question", userService.getLoggedInUser());
		gameChange.setGame(course.getGameByID(gameID));
		course.getGameByID(gameID).addGameChange(gameChange);
		/**************************/
		
		courseService.saveCourse(course);  // update the course back
		
		return "redirect:/game?courseID=" + courseID + "&gameID=" + gameID; // redirect to game
	}
}
