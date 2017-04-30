package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.model.Course;
import play_and_learn.model.Game;
import play_and_learn.model.Question;
import play_and_learn.service.AuthenticationService;
import play_and_learn.service.CourseService;

@Controller
public class AddQuestionController {
	@Autowired
	private CourseService courseService;
	@Autowired
    private AuthenticationService authService;
	
	@GetMapping("/addquestion")
	public String openQuestionForm(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
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
		
		courseService.saveCourse(course);  // update the course back
		
		return "redirect:/game?courseID=" + courseID + "&gameID=" + gameID; // redirect to game
	}
}
