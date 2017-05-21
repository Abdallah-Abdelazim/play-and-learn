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
import play_and_learn.model.GameChange;
import play_and_learn.model.GameFactory;
import play_and_learn.service.CourseService;
import play_and_learn.service.GameService;
import play_and_learn.service.UserService;

@Controller
public class EditGameController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	
	@GetMapping("/editgame")
	public String openEditGameForm(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		// check if there is no logged in user & the user must be a teacher
		if (userService.getLoggedInUser() ==null) {
			model.addAttribute("error", "Please log in first.");
			return "unauthorized-access";
		}
		else if ( !(  (userService.getLoggedInUser().equals( ( gameService.findByID(gameID) ).getCreatorTeacherUsername() ) || (userService.getLoggedInUser().equals( ( gameService.findByID(gameID) ).getGameCollaboratorTeacherUsername())) ) ) ) {
			// the current user neither created the game nor is a collaborator
			model.addAttribute("error", "You must be either a creator of the game or a collaborator in order to be able to edit it.");
			return "unauthorized-access";
		}
		
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("gameID", gameID);
		model.addAttribute("game", GameFactory.getGame("Q/A"));
		
		return "editgame";			
		
	}
	
	@PostMapping("/editgame")
	public String editGame(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID
			, @ModelAttribute("game") Game game
			, Model model) {
		
		Course updatedCourse = courseService.findByID(courseID);
		
		if (!(game.getName().equals(""))) {
			updatedCourse.getGameByID(gameID).setName(game.getName());
		}
		if (!(game.getDescription().equals(""))) {
			updatedCourse.getGameByID(gameID).setDescription(game.getDescription());
		}
		if (!(game.getGameType().equals(""))) {
			updatedCourse.getGameByID(gameID).setGameType(game.getGameType());
		}
		if (!(game.getGameLevel().equals(""))) {
			updatedCourse.getGameByID(gameID).setGameLevel(game.getGameLevel());;
		}
		if (!(game.getNumOfQuestions() == 0)) {
			updatedCourse.getGameByID(gameID).setNumOfQuestions(game.getNumOfQuestions());
		}
		
		/* Record the game change */
		GameChange gameChange = new GameChange("Edit Game", userService.getLoggedInUser());
		gameChange.setGame(updatedCourse.getGameByID(gameID));
		updatedCourse.getGameByID(gameID).addGameChange(gameChange);
		/**************************/
		
		courseService.saveCourse(updatedCourse);
		
	
		return "redirect:/game?courseID=" + courseID + "&gameID=" + gameID; // redirects
	}
	
	@GetMapping("/deletequestion")
	public String openDeleteQuestionPage(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		// check if there is no logged in user & the user must be a teacher
		if (userService.getLoggedInUser() ==null) {
			model.addAttribute("error", "Please log in first.");
			return "unauthorized-access";
		}
		else if ( !(  (userService.getLoggedInUser().equals( ( gameService.findByID(gameID) ).getCreatorTeacherUsername() ) || (userService.getLoggedInUser().equals( ( gameService.findByID(gameID) ).getGameCollaboratorTeacherUsername())) ) ) ) {
			// the current user neither created the game nor is a collaborator
			model.addAttribute("error", "You must be either a creator of the game or a collaborator in order to be able to edit it.");
			return "unauthorized-access";
		}
		
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("gameID", gameID);
		
		// TODO make it ready for deletequestion template
		
		return "deletequestion";			
		
	}
	
	
	@PostMapping("/deletequestion")
	public String deleteQuestion(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		// TODO delete the question
		
		return "redirect:/game?courseID=" + courseID + "&gameID=" + gameID; // redirects
		
	}

}
