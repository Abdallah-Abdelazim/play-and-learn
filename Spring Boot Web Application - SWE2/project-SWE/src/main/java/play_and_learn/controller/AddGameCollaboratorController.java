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
import play_and_learn.model.User;
import play_and_learn.service.CourseService;
import play_and_learn.service.GameService;
import play_and_learn.service.UserService;

@Controller
public class AddGameCollaboratorController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	
	@GetMapping("/addcollaborator")
	public String openAddCollaboratorForm(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID
			, Model model) {
		
		// check if there is no logged in user & the user must be a teacher
				if (userService.getLoggedInUser() ==null) {
					model.addAttribute("error", "Please log in first.");
					return "unauthorized-access";
				}
				else if ( !(  (userService.getLoggedInUser().equals( ( gameService.findByID(gameID) ).getCreatorTeacherUsername()))  )  ) {
					// the current user is not the creator
					model.addAttribute("error", "You must be the creator of the game in order to be able to add a collaborator.");
					return "unauthorized-access";
				}
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("gameID", gameID);
		model.addAttribute("collaboratorTeacher", new User());
		
		return "addcollaborator";
	}
	
	@PostMapping
	public String addCollaborator(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID
			, @ModelAttribute("collaboratorTeacher") User collaboratorTeacher
			, Model model) {
		
		Course course = courseService.findByID(courseID);
		course.getGameByID(gameID).setGameCollaboratorTeacherUsername(collaboratorTeacher.getUsername());
		
		/* Record the game change */
		GameChange gameChange = new GameChange("Add Collaborator", userService.getLoggedInUser());
		gameChange.setGame(course.getGameByID(gameID));
		course.getGameByID(gameID).addGameChange(gameChange);
		/**************************/
		
		courseService.saveCourse(course);
		
		return "redirect:/game?courseID=" + courseID + "&gameID=" + gameID; // redirect to game
	}

}
